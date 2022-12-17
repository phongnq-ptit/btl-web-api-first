package com.example.phongpt176.services;

import com.example.phongpt176.models.Carts;
import com.example.phongpt176.models.ResponseObject;
import java.util.List;

public interface ICartService {
  ResponseObject<List<Carts>> getCartByUser(Long userId);

  ResponseObject<Carts> createCartByUser(Carts cart);

  ResponseObject<Carts> updateCart(Carts cartUpdate, Long id);

  ResponseObject<Object> deleteCart(Long id);
}
