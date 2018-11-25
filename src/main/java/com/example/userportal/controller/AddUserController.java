package com.example.userportal.controller;

import com.example.userportal.domain.User;
import com.example.userportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.55:4200", "http://192.168.0.55:8081", "http://localhost:8081"}, maxAge = 3600)
@RestController
//@RequestMapping({"/api"})
@RequestMapping({"/add"})
public class AddUserController {

  private final UserService userService;

  @Autowired
  public AddUserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public User pay() {

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
