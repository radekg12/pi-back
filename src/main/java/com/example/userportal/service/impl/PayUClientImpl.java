package com.example.userportal.service.impl;

import com.example.userportal.RequestModel.PayUOrderRequest;
import com.example.userportal.RequestModel.PayUOrderResponseModel;
import com.example.userportal.RequestModel.PayUProduct;
import com.example.userportal.RequestModel.PayUResponse;
import com.example.userportal.domain.*;
import com.example.userportal.service.*;
import com.example.userportal.service.dto.PayUOrderDTO;
import com.example.userportal.service.dto.ProductDTO;
import com.example.userportal.service.dto.ShoppingCartPositionDTO;
import com.example.userportal.service.mapper.AddressMapper;
import com.example.userportal.service.mapper.DeliveryTypeMapper;
import com.example.userportal.service.mapper.PaymentMethodMapper;
import com.example.userportal.service.mapper.ProductMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PayUClientImpl implements PayUClient {
  private static final String PAYU_AUTHORIZE_URL = "https://secure.snd.payu.com/pl/standard/user/oauth/authorize";
  private static final String PAYU_ORDERS_URL = "https://secure.snd.payu.com/api/v2_1/orders/";
  private final ShoppingCartService shoppingCartService;
  private final OrderService orderService;
  private final CustomerService customerService;
  private final OrderStatusService orderStatusService;
  private final AddressService addressService;
  @Value("${payu.clientId}")
  private String clientId;
  @Value("${payu.clientSecret}")
  private String clientSecret;
  @Value("${payu.pos_id}")
  private String posId;
  private ProductMapper productMapper;
  private AddressMapper addressMapper;
  private DeliveryTypeMapper deliveryTypeMapper;
  private PaymentMethodMapper paymentMethodMapper;

  @Autowired
  public PayUClientImpl(ShoppingCartService shoppingCartService, OrderService orderService, CustomerService customerService, OrderStatusService orderStatusService, AddressService addressService, ProductMapper productMapper, AddressMapper addressMapper, DeliveryTypeMapper deliveryTypeMapper, PaymentMethodMapper paymentMethodMapper) {
    this.shoppingCartService = shoppingCartService;
    this.orderService = orderService;
    this.customerService = customerService;
    this.orderStatusService = orderStatusService;
    this.addressService = addressService;
    this.productMapper = productMapper;
    this.addressMapper = addressMapper;
    this.deliveryTypeMapper = deliveryTypeMapper;
    this.paymentMethodMapper = paymentMethodMapper;
  }

  @Override
  public PayUOrderResponseModel payForOrder(int customerId, HttpServletRequest req, PayUOrderDTO payUOrderDTO) {
    PayUResponse payUResponse;
    PayUOrderResponseModel orderResponse = null;
    try {
      payUResponse = createPayment(customerId);
      orderResponse = completePayment(customerId, req, payUResponse, payUOrderDTO);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return orderResponse;
  }

  @Override
  public PayUResponse createPayment(int customerId) throws IOException {
    Map<String, String> parameters = new HashMap<>();
    parameters.put("grant_type", "client_credentials");
    parameters.put("client_id", clientId);
    parameters.put("client_secret", clientSecret);

    HttpClient request = HttpClientBuilder.create().build();
    HttpPost post = new HttpPost(URI.create(buildUrlWithParams(PAYU_AUTHORIZE_URL, parameters)));
    StringEntity entity = new StringEntity("", ContentType.APPLICATION_JSON);
    post.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
    post.setEntity(entity);
    HttpResponse httpResponse = request.execute(post);

    Gson gson = new GsonBuilder().create();
    PayUResponse response = gson.fromJson(EntityUtils.toString(httpResponse.getEntity()), PayUResponse.class);

    return response;
  }

  @Override
  public PayUOrderResponseModel completePayment(int customerId, HttpServletRequest req, PayUResponse payUResponse, PayUOrderDTO payUOrderDTO) throws IOException {

    Iterable<ShoppingCartPositionDTO> shoppingCartPositionDtos = shoppingCartService.getAllPositions(customerId);

    List<PayUProduct> products = new ArrayList<>();
    shoppingCartPositionDtos.forEach(p -> {
      ProductDTO product = p.getProduct();
      PayUProduct payUProduct = PayUProduct.builder()
              .name(product.getName())
              .unitPrice(product.getUnitPrice())
              .quantity(p.getQuantity())
              .build();
      products.add(payUProduct);
    });

    int totalAmount = products.stream()
            .map(x -> x.getQuantity() * x.getUnitPrice())
            .reduce((x, y) -> x + y)
            .orElse(0);
    totalAmount += payUOrderDTO.getDeliveryType().getPrice();
    totalAmount += payUOrderDTO.getPaymentMethod().getPrice();

    String ipAddress = req.getRemoteAddr();

    List<OrderPosition> orderProducts = new ArrayList<>();
    shoppingCartPositionDtos.forEach(p -> {
      ProductDTO productDto = p.getProduct();
      OrderPosition orderPosition = OrderPosition.builder()
              .productByProductId(productMapper.toProduct(productDto))
              .quantity(p.getQuantity())
              .unitPrice(p.getProduct().getUnitPrice())
              .build();
      orderProducts.add(orderPosition);
    });

    OrderStatus orderStatus = orderStatusService.getStatusById(1);
    Customer customer = customerService.getCustomer(customerId);
    Address address = addressService.save(addressMapper.toAddress(payUOrderDTO.getAddress()));


    Order order = Order.builder()
            .dateOfOrder(Timestamp.valueOf(LocalDateTime.now()))
            .dateOfDelivery(Timestamp.valueOf(LocalDateTime.now().plusDays(3)))
            .orderPositionsById(orderProducts)
            .addressByDeliveryAddressId(address)
            .deliveryTypeByDeliveryTypeId(deliveryTypeMapper.toDeliveryType(payUOrderDTO.getDeliveryType()))
            .paymentMethodByPaymentMethodId(paymentMethodMapper.toPaymentMethod(payUOrderDTO.getPaymentMethod()))
            .totalAmount(totalAmount)
            .orderStatusByOrderStatusId(orderStatus)
            .customerByCustomerId(customer)
            .build();

    orderService.saveOrderAndCleanShoppingCart(order, customerId);

    PayUOrderRequest orderBody = PayUOrderRequest.builder()
            .notifyUrl("http://localhost:4200/")
            .successCallback("http://localhost:4200/")
            .customerIp(ipAddress)
            .merchantPosId(posId)
            .description("Hurtpol - zakup sprzętu komputerowego")
            .currencyCode("PLN")
            .totalAmount(totalAmount)
            .products(products)
            .build();

    Gson gson = new GsonBuilder().create();
    String body = gson.toJson(orderBody);


    HttpClient request = HttpClientBuilder.create().build();
    HttpPost post = new HttpPost(URI.create(PAYU_ORDERS_URL));
    StringEntity entity = new StringEntity(body, ContentType.APPLICATION_JSON);
    post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    post.setHeader(HttpHeaders.AUTHORIZATION, payUResponse.getToken_type() + " " + payUResponse.getAccess_token());
    post.setEntity(entity);
    HttpResponse httpResponse = request.execute(post);

    PayUOrderResponseModel response = gson.fromJson(EntityUtils.toString(httpResponse.getEntity()), PayUOrderResponseModel.class);

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
