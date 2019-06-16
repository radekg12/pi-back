package com.example.userportal.controller;

import com.example.userportal.service.ShopService;
import com.example.userportal.service.dto.ShopDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/{lat}-{lng}")
    public List<ShopDTO> getClosestShops(@RequestParam("lat") double lat,
                                         @RequestParam("lng") double lng) {
        return shopService.findClosestShops(lat, lng);
    }

}
