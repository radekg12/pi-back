package com.example.userportal.service.impl;

import com.example.userportal.domain.*;
import com.example.userportal.requestmodel.payu.*;
import com.example.userportal.security.SecurityUtils;
import com.example.userportal.service.*;
import com.example.userportal.service.dto.*;
import com.example.userportal.service.mapper.AddressMapper;
import com.example.userportal.service.mapper.DeliveryTypeMapper;
import com.example.userportal.service.mapper.PaymentMethodMapper;
import com.example.userportal.service.mapper.ProductMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PayUClientImpl implements PayUClient {
  private final ShoppingCartService shoppingCartService;
  private final OrderService orderService;
  private final CustomerService customerService;
  private final OrderStatusService orderStatusService;
  private final AddressService addressService;
  private final PaymentMethodService paymentMethodService;
  private final DeliveryTypeService deliveryTypeService;

  private final ProductMapper productMapper;
  private final AddressMapper addressMapper;
  private final DeliveryTypeMapper deliveryTypeMapper;
  private final PaymentMethodMapper paymentMethodMapper;
  private final HttpServletRequest request;

  @Value("${payu.clientId}")
  private String clientId;
  @Value("${payu.clientSecret}")
  private String clientSecret;
  @Value("${payu.pos_id}")
  private String posId;
  @Value("${payu.secondKey}")
  private String secondKey;
  @Value("${payu.authorizeUrl}")
  private String authorizeUrl;
  @Value("${payu.ordersUrl}")
  private String ordersUrl;
  @Value("${payu.continueUrl}")
  private String continueUrl;
  @Value("${payu.notifyUrl}")
  private String notifyUrl;


  @Override
  public PayUOrderCreateResponse payForOrder(PayUOrderDTO payUOrderDTO) {
    Integer customerId = SecurityUtils.getCurrentUserId();
    PayUAuthenticationResponse payUAuthenticationResponse = createPayment(customerId);
    return completePayment(payUAuthenticationResponse.getAuthenticationValue(), payUOrderDTO, null);
  }

  @Override
  public PayUOrderCreateResponse payForOrderWithGooglePay(GooglePayOrderDTO googlePayOrderDTO) {
    Integer customerId = SecurityUtils.getCurrentUserId();
    PayUAuthenticationResponse payUAuthenticationResponse = createPayment(customerId);

    PaymentMethodDTO paymentMethod = paymentMethodService.findById(4);
    DeliveryTypeDTO deliveryType = deliveryTypeService.findById(3);

    PayUOrderDTO payUOrder = new PayUOrderDTO()
            .setAddress(googlePayOrderDTO.getAddress())
            .setDeliveryType(deliveryType)
            .setPaymentMethod(paymentMethod);

    return completePayment(payUAuthenticationResponse.getAuthenticationValue(), payUOrder, googlePayOrderDTO.getGooglePaymentTokenBase64());
  }

  @Override
  public PayUAuthenticationResponse createPayment(int customerId) {
    Map<String, String> parameters = new HashMap<>();
    parameters.put("grant_type", "client_credentials");
    parameters.put("client_id", clientId);
    parameters.put("client_secret", clientSecret);

    return new RestTemplate().postForObject(
            buildUrlWithParams(authorizeUrl, parameters),
            "",
            PayUAuthenticationResponse.class);
  }

  private String buildUrlWithParams(String url, Map<String, String> params) {
    return url + buildParams(params);
  }

  private String buildParams(Map<String, String> params) {
    return params.entrySet().stream()
            .map(e -> e.getKey() + "=" + e.getValue())
            .collect(Collectors.joining("&", "?", ""));
  }

  @Override
  @Transactional
  public PayUOrderCreateResponse completePayment(String authenticationValue, PayUOrderDTO payUOrderDTO, String googlePaymentToken) {

    Integer customerId = SecurityUtils.getCurrentUserId();
    List<ShoppingCartPositionDTO> shoppingCartPositionDtos = shoppingCartService.getAllCurrentCustomerPositions();

    List<PayUProduct> payUProducts = createPayUProductList(shoppingCartPositionDtos);
    int totalAmount = calculateTotalAmount(payUProducts, payUOrderDTO);
    OrderStatus orderStatus = orderStatusService.getStatusById(1);
    Customer customer = customerService.getCustomer(customerId);

    Address address = addressService.save(addressMapper.toAddress(payUOrderDTO.getAddress()));
    Order order = saveOrder(shoppingCartPositionDtos, payUOrderDTO, customer, address, orderStatus, totalAmount);

    PayUOrderCreateRequest orderCreateRequest = createOrderCreateRequest(order.getId(), totalAmount, payUProducts, customer, googlePaymentToken);

    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.AUTHORIZATION, authenticationValue);
    HttpEntity<PayUOrderCreateRequest> request = new HttpEntity<>(orderCreateRequest, headers);

    return new RestTemplate().postForObject(
            ordersUrl,
            request,
            PayUOrderCreateResponse.class);
  }

  private PayUOrderCreateRequest createOrderCreateRequest(int orderId,
                                                          int totalAmount,
                                                          List<PayUProduct> payUProducts,
                                                          Customer customer,
                                                          String googlePaymentToken) {
    String ipAddress = request.getRemoteHost();
    PayUOrderCreateRequest orderCreateRequest = PayUOrderCreateRequest.builder()
            .extOrderId(orderId)
            .continueUrl(continueUrl)
            .notifyUrl(notifyUrl)
            .customerIp(ipAddress)
            .merchantPosId(posId)
            .description("Hurtpol - zakup sprzętu komputerowego")
            .currencyCode("PLN")
            .totalAmount(totalAmount)
            .products(payUProducts.toArray(new PayUProduct[0]))
            .buyer(Buyer.builder()
                    .email(customer.getEmail())
                    .phone(customer.getPhoneNumber())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .language("pl")
                    .build())
            .settings(Settings.builder()
                    .invoiceDisabled(true)
                    .build())
            .build();

    if (googlePaymentToken != null) {
      orderCreateRequest.setPayMethods(PayMethods.builder()
              .payMethod(PayMethod.builder()
                      .value("ap")
                      .type("PBL")
                      .authorizationCode(googlePaymentToken)
                      .build())
              .build());
    }

    return orderCreateRequest;
  }

  private Order saveOrder(List<ShoppingCartPositionDTO> shoppingCartPositionDtos, PayUOrderDTO payUOrderDTO, Customer customer, Address address, OrderStatus orderStatus, int totalAmount) {
    List<OrderPosition> orderProducts = new ArrayList<>();
    shoppingCartPositionDtos.forEach(p -> {
      ProductDTO productDto = p.getProduct();
      OrderPosition orderPosition = OrderPosition.builder()
              .product(productMapper.toProduct(productDto))
              .quantity(p.getQuantity())
              .unitPrice(p.getProduct().getUnitPrice())
              .build();
      orderProducts.add(orderPosition);
    });

    Order order = Order.builder()
            .dateOfOrder(Timestamp.valueOf(LocalDateTime.now()))
            .dateOfDelivery(Timestamp.valueOf(LocalDateTime.now().plusDays(3)))
            .orderPositions(orderProducts)
            .address(address)
            .deliveryType(deliveryTypeMapper.toDeliveryType(payUOrderDTO.getDeliveryType()))
            .paymentMethod(paymentMethodMapper.toPaymentMethod(payUOrderDTO.getPaymentMethod()))
            .totalAmount(totalAmount)
            .orderStatus(orderStatus)
            .customer(customer)
            .build();

    orderService.saveOrderAndCleanShoppingCart(order, customer.getId());
    return order;
  }

  private List<PayUProduct> createPayUProductList(List<ShoppingCartPositionDTO> shoppingCartPositionDtos) {
    ArrayList<PayUProduct> products = new ArrayList<>();
    shoppingCartPositionDtos.forEach(p -> {
      ProductDTO product = p.getProduct();
      PayUProduct payUProduct = PayUProduct.builder()
              .name(product.getName())
              .unitPrice(product.getUnitPrice())
              .quantity(p.getQuantity())
              .build();
      products.add(payUProduct);
    });
    return products;
  }

  private int calculateTotalAmount(List<PayUProduct> payUProducts, PayUOrderDTO payUOrderDTO) {
    Integer total = payUProducts.stream()
            .mapToInt(x -> x.getQuantity() * x.getUnitPrice())
            .sum();
    total += payUOrderDTO.getDeliveryType().getPrice();
    total += payUOrderDTO.getPaymentMethod().getPrice();
    return total;
  }

  @Override
  public OrderDTO finalizePayment(String signatureHeader, PayUOrderNotifyRequest notify) {
    log.info("PAYU NOTIFY");
    log.info(signatureHeader);
    log.info(notify.getOrder().getStatus().name());
    log.info(notify.getOrder().getExtOrderId());

    boolean verifed = signatureNotificationIsVerified(signatureHeader, notify);

    OrderDTO order = null;
    switch (notify.getOrder().getStatus()) {
      case PENDING:
      case CANCELED:
        order = orderService.updateOrderStatus(Integer.parseInt(notify.getOrder().getExtOrderId()), 5);
        break;
      case COMPLETED:
        order = orderService.updateOrderStatus(Integer.parseInt(notify.getOrder().getExtOrderId()), 2);
        break;
    }
    return order;
  }

  private boolean signatureNotificationIsVerified(String signatureHeader, PayUOrderNotifyRequest notify) {
    Map<String, String> signatures = Arrays.stream(signatureHeader.split(";"))
            .map(value -> value.split("="))
            .collect(Collectors.toMap(split -> split[0], split -> split[1]));

    String incomingSignature = signatures.get("signature");

    Gson gson = new GsonBuilder().create();
    String jsonNotification = gson.toJson(notify);
    String concatenated = jsonNotification + secondKey;
    String expectedSignature = encodeMd5(concatenated);

    if (expectedSignature.equals(incomingSignature)) {
      log.info("Correct signature");
      return true;
    } else {
      log.info("Wrong signature");
      return false;
    }
  }

  private String encodeMd5(String concatenated) {
    String md5;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(concatenated.getBytes());
      byte[] digest = md.digest();
      md5 = new BigInteger(1, digest).toString(16);
      return md5;
    } catch (NoSuchAlgorithmException e) {
      return "";
    }
  }
}
