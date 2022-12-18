package com.example.phongpt176.controllers;

import com.example.phongpt176.models.Carts;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.services.impl.CartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/cart")
public class CartController {
  @Autowired
  private CartService cartService;

  @GetMapping()
  public ResponseEntity<ResponseObject<List<Carts>>> getCartByUser(
      @RequestParam( name = "userId") String userId) {
    return ResponseEntity.ok().body(cartService.getCartByUser(Long.parseLong(userId)));
  }

  @PostMapping()
  public ResponseEntity<ResponseObject<Carts>> createCartByUser(@RequestBody Carts cart) {
    return ResponseEntity.ok().body(cartService.createCartByUser(cart));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ResponseObject<Carts>> updateCartItem(
      @RequestBody Carts cartUpdate, @PathVariable("id") Long id
  ) {
    return ResponseEntity.ok().body(cartService.updateCart(cartUpdate, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseObject<Object>> deleteCartItem(@PathVariable("id") Long id) {
    return ResponseEntity.ok().body(cartService.deleteCart(id));
  }
}
