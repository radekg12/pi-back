package com.example.userportal.controller;

import com.example.userportal.domain.Product;
import com.example.userportal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping({"/add"})
public class AddProductController {

  private final ProductService productService;

  @Autowired
  public AddProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public Product pay() {

    Client client = ClientBuilder.newClient();
    Entity<String> payload = Entity.text("grant_type=client_credentials&#38;client_id=145227&#38;client_secret=12f071174cb7eb79d4aac5bc2f07563f");
    Response response = client.target("https://secure.payu.com/pl/standard/user/oauth/authorize")
            .request(MediaType.TEXT_PLAIN_TYPE)
            .post(payload);

    System.out.println("status: " + response.getStatus());
    System.out.println("headers: " + response.getHeaders());
    System.out.println("body:" + response.readEntity(String.class));

    return null;
  }


}
