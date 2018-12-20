package com.example.userportal.controller;


import com.example.userportal.service.MenuService;
import com.example.userportal.service.dto.ProductCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/menu"})
public class MenuController {

  private final MenuService menuService;

  @Autowired
  public MenuController(MenuService menuService) {
    this.menuService = menuService;
  }

  @GetMapping
  public Iterable<ProductCategoryDTO> findAllByPage() {
    return menuService.findAll();
  }
}
