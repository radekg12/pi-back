package com.example.userportal.controller;

import com.example.userportal.domain.User;
import com.example.userportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.55:4200", "http://192.168.0.55:8081", "http://localhost:8081"}, maxAge = 3600)
@RestController
//@RequestMapping({"/api"})
@RequestMapping({"/users"})
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public User create(@RequestBody User user) {
    return userService.create(user);
  }

  @GetMapping(path = {"/{id}"})
  public Optional<User> findOne(Model model, @PathVariable("id") Long id) {
    return userService.findById(id);
  }

  @GetMapping
  public Page<User> findAllByPage(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                  @RequestParam(value = "per_page", required = false, defaultValue = "2") int size,
                                  @RequestParam(value = "sort_by", required = false, defaultValue = "id_asc") String sort) {
    return userService.findPaginated(page, size, sort);
  }

  @PutMapping
  public User update(@RequestBody User user) {
    return userService.update(user);
  }

  @DeleteMapping(path = "/{id}")
  public User delete(@PathVariable("id") Long id) {
    return userService.delete(id);
  }

//  @GetMapping
//  public Iterable<User> findAll() {
//    return userService.findAll();
//  }

}
