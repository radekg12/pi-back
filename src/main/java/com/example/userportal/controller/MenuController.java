package com.example.userportal.controller;

import com.example.userportal.service.MenuService;
import com.example.userportal.service.dto.ProductCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<ProductCategoryDTO> findAll() {
        return menuService.findAll();
    }

    @GetMapping("/{id}")
    public ProductCategoryDTO findBySubcategoryId(@PathVariable("id") int subcategoryId) {
        return menuService.findBySubcategoryId(subcategoryId);
    }
}
