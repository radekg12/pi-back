package com.example.userportal.service.impl;

import com.example.userportal.service.PayUClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayUClientImpl implements PayUClient {
  private static final String PAYU_AUTHORIZE_URL = "https://secure.payu.com/pl/standard/user/oauth/authorize";
  private static final String PAYU_ORDERS_URL = "https://secure.payu.com/api/v2_1/orders/";
  @Value("${payu.clientId}")
  private String clientId;
  @Value("${payu.clientSecret}")
  private String clientSecret;

  public String createPayment() {

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

//    client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//            .thenApply(HttpResponse::body)
//            .thenAccept(System.out::println)
//            .join();

    HttpResponse<String> result = null;
    try {
      result = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    result.body();

    Gson gson = new GsonBuilder().create();
    PayUResponse response = gson.fromJson(result.body(), PayUResponse.class);
    String accessToken = response.getAccess_token();


    return result.body();
  }

  public String completePayment(HttpServletRequest req) {

    String body = "{\n" +
            "  \"notifyUrl\": \"https://your.eshop.com/notify\",\n" +
            "  \"customerIp\": \"127.0.0.1\",\n" +
            "  \"merchantPosId\": \"145227\",\n" +
            "  \"description\": \"RTV market\",\n" +
            "  \"currencyCode\": \"PLN\",\n" +
            "  \"totalAmount\": \"21000\",\n" +
            "  \"products\": [\n" +
            "    {\n" +
            "      \"name\": \"Wireless mouse\",\n" +
            "      \"unitPrice\": \"15000\",\n" +
            "      \"quantity\": \"1\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"HDMI cable\",\n" +
            "      \"unitPrice\": \"6000\",\n" +
            "      \"quantity\": \"1\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    HttpRequest request = HttpRequest.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .timeout(Duration.ofSeconds(10))
            .uri(URI.create(PAYU_ORDERS_URL))
            .headers("Content-Type", "application/json", "Authorization", "Bearer 2cb1d9a8-8c0a-49dd-b0d3-1f8ccf5d4ec4")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();
    HttpClient client = HttpClient.newHttpClient();

//    client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//            .thenApply(HttpResponse::body)
//            .thenAccept(System.out::println)
//            .join();

    HttpResponse<String> result = null;
    try {
      result = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

    return result.body();
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
