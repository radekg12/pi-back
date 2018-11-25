package com.example.userportal.service.impl;

import com.example.userportal.service.PayPalClient;
import com.google.gson.Gson;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayPalClientImpl implements PayPalClient {
  private static final String PAYPAL_SUCCESS_URL = "http://localhost:4200/add";
  private static final String PAYPAL_CANCEL_URL = "http://localhost:4200/";
  @Value("${paypal.clientId}")
  private String clientId;
  @Value("${paypal.clientSecret}")
  private String clientSecret;
  @Value("${paypal.mode}")
  private String mode;

  public Map<String, Object> createPayment(String sum) {
    Map<String, Object> response = new HashMap<>();
    Amount amount = new Amount();
    amount.setCurrency("PLN");
    amount.setTotal(sum);
    Transaction transaction = new Transaction();
    transaction.setAmount(amount);
    List<Transaction> transactions = new ArrayList<>();
    transactions.add(transaction);

    Payer payer = new Payer();
    payer.setPaymentMethod("paypal");

    Payment payment = new Payment();
    payment.setIntent("sale");
    payment.setPayer(payer);
    payment.setTransactions(transactions);

    RedirectUrls redirectUrls = new RedirectUrls();
    redirectUrls.setCancelUrl(PAYPAL_CANCEL_URL);
    redirectUrls.setReturnUrl(PAYPAL_SUCCESS_URL);
    payment.setRedirectUrls(redirectUrls);
    Payment createdPayment;
    try {
      String redirectUrl = "";
      APIContext context = new APIContext(clientId, clientSecret, mode);
      createdPayment = payment.create(context);
      if (createdPayment != null) {
        List<Links> links = createdPayment.getLinks();
        String id = createdPayment.getId();
        for (Links link : links) {
          if (link.getRel().equals("approval_url")) {
            redirectUrl = link.getHref();
            break;
          }
        }
        response.put("paymentID", id);
        response.put("status", "success");
        response.put("redirect_url", redirectUrl);
      }
    } catch (PayPalRESTException e) {
      System.out.println("Error happened during payment creation!");
    }
    return response;
  }

  public String completePayment(HttpServletRequest req) {
    Map<String, Object> response = new HashMap<>();
    Payment payment = new Payment();
    payment.setId(req.getParameter("paymentID"));

    PaymentExecution paymentExecution = new PaymentExecution();
    paymentExecution.setPayerId(req.getParameter("payerID"));
    try {
      APIContext context = new APIContext(clientId, clientSecret, "sandbox");
      Payment createdPayment = payment.execute(context, paymentExecution);
      if (createdPayment != null) {
        response.put("status", "success");
        response.put("payment", createdPayment);
      }
    } catch (PayPalRESTException e) {
      System.err.println(e.getDetails());
    }


    Gson gson = new Gson();
    String xx = gson.toJson(response);
    return xx;
  }
}
