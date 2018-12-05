package com.example.userportal.service.impl;

import com.example.userportal.RequestModel.PayUOrderRequest;
import com.example.userportal.RequestModel.PayUOrderResponseModel;
import com.example.userportal.RequestModel.PayUProduct;
import com.example.userportal.RequestModel.PayUResponse;
import com.example.userportal.domain.Product;
import com.example.userportal.domain.ShoppingCartPosition;
import com.example.userportal.service.PayUClient;
import com.example.userportal.service.ShoppingCartService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayUClientImpl implements PayUClient {
  private static final String PAYU_AUTHORIZE_URL = "https://secure.snd.payu.com/pl/standard/user/oauth/authorize";
  private static final String PAYU_ORDERS_URL = "https://secure.snd.payu.com/api/v2_1/orders/";
  @Value("${payu.clientId}")
  private String clientId;
  @Value("${payu.clientSecret}")
  private String clientSecret;
  private final ShoppingCartService shoppingCartService;
  @Value("${payu.pos_id}")
  private String posId;

  @Autowired
  public PayUClientImpl(ShoppingCartService shoppingCartService) {
    this.shoppingCartService = shoppingCartService;
  }

  @Override
  public PayUOrderResponseModel payForOrder(int customerId, HttpServletRequest req) {
    PayUResponse payUResponse = null;
    PayUOrderResponseModel orderResponse = null;
    try {
      payUResponse = createPayment(customerId);
      orderResponse = completePayment(customerId, req, payUResponse);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return orderResponse;
  }

  @Override
  public PayUResponse createPayment(int customerId) throws IOException, InterruptedException {
    Map<String, String> parameters = new HashMap<>();
    parameters.put("grant_type", "client_credentials");
    parameters.put("client_id", clientId);
    parameters.put("client_secret", clientSecret);

    HttpRequest request = HttpRequest.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .timeout(Duration.ofSeconds(10))
            .uri(URI.create(buildUrlWithParams(PAYU_AUTHORIZE_URL, parameters)))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.noBody())
            .build();
    HttpClient client = HttpClient.newHttpClient();

    HttpResponse<String> result = client.send(request, HttpResponse.BodyHandlers.ofString());
    result.body();

    Gson gson = new GsonBuilder().create();
    PayUResponse response = gson.fromJson(result.body(), PayUResponse.class);

    return response;
  }

  @Override
  public PayUOrderResponseModel completePayment(int customerId, HttpServletRequest req, PayUResponse payUResponse) throws IOException, InterruptedException {

    Iterable<ShoppingCartPosition> shoppingCartPositions = shoppingCartService.getAllPositions(customerId);

    List<PayUProduct> products = new ArrayList<>();
    shoppingCartPositions.forEach(p -> {
      Product product = p.getProductByProductId();
      PayUProduct payUProduct = PayUProduct.builder()
              .name(product.getName())
              .unitPrice(product.getUnityPrice())
              .quantity(p.getQuantity())
              .build();
      products.add(payUProduct);
    });

    int totalAmount = products.stream()
            .map(x -> x.getQuantity() * x.getUnitPrice())
            .reduce((x, y) -> x + y)
            .orElse(0);

    String ipAddress = req.getRemoteAddr();

    PayUOrderRequest orderBody = PayUOrderRequest.builder()
            .notifyUrl("hurtpol.com")
            .customerIp(ipAddress)
            .merchantPosId(posId)
            .description("Hurtpol")
            .currencyCode("PLN")
            .totalAmount(totalAmount)
            .products(products)
            .build();

    Gson gson = new GsonBuilder().create();
    String body = gson.toJson(orderBody);


    HttpRequest request = HttpRequest.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .timeout(Duration.ofSeconds(10))
            .uri(URI.create(PAYU_ORDERS_URL))
            .headers("Content-Type", "application/json", "Authorization", payUResponse.getToken_type() + " " + payUResponse.getAccess_token())
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();
    HttpClient client = HttpClient.newHttpClient();


    HttpResponse<String> result = client.send(request, HttpResponse.BodyHandlers.ofString());
    PayUOrderResponseModel response = gson.fromJson(result.body(), PayUOrderResponseModel.class);
    return response;
  }

  private String buildParams(Map<String, String> params) {
    return params.entrySet().stream()
            .map(e -> e.getKey() + "=" + e.getValue())
            .collect(Collectors.joining("&", "?", ""));
  }

  private String buildUrlWithParams(String url, Map<String, String> params) {
    return url + buildParams(params);
  }
}
