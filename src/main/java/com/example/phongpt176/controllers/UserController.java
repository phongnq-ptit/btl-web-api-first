package com.example.phongpt176.controllers;

import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.models.Users;
import com.example.phongpt176.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<ResponseObject<Users>> login(@RequestBody Users user) {
    return ResponseEntity.ok().body(userService.login(user));
  }

  @PostMapping("/register")
  public ResponseEntity<ResponseObject<Users>> register(@RequestBody Users user) {
    return ResponseEntity.ok().body(userService.register(user));
  }
}
