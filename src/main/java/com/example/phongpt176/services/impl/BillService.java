package com.example.phongpt176.services.impl;

import com.example.phongpt176.models.Bills;
import com.example.phongpt176.models.Carts;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.models.Users;
import com.example.phongpt176.models.dto.BillDto;
import com.example.phongpt176.repositories.BillRepo;
import com.example.phongpt176.repositories.CartRepo;
import com.example.phongpt176.repositories.UserRepo;
import com.example.phongpt176.services.IBillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService implements IBillService {

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  private BillRepo billRepo;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private CartRepo cartRepo;

  @Autowired
  private CartService cartService;

  @Autowired
  private BookService bookService;

  @Override
  public ResponseObject<ArrayList<Bills>> getHistoryByUser(Long userId) {
    try {
      Optional<Users> user = userRepo.findById(userId);

      if (!user.isPresent()) {
        return new ResponseObject("Không tìm thấy người dùng!", null);
      }

      ArrayList<Bills> bills = billRepo.findAllByUserId(userId);

      if (bills.size() > 0) {
        for (Bills item: bills) {
          Map<String, Object> listBooks = objectMapper.readValue(item.getListBooks(), Map.class);
          ArrayList<Carts> tmp = new ArrayList<>();
          item.setInfo(objectMapper.readValue(item.getUserInfo(), Map.class));

          ArrayList<Integer> cartIds = (ArrayList<Integer>) listBooks.get("carts");

          for (var cartId: cartIds) {
            tmp.add(this.getCart(cartId.longValue()));
          }
          item.setListProducts(tmp);
        }
      }

      return new ResponseObject<ArrayList<Bills>>(
          "Lấy ra hóa đơn của user " + userId + " thành công!!",
          bills
      );
    } catch (Exception e) {
      return new ResponseObject<ArrayList<Bills>>(
          e.getMessage(),
          null
      );
    }
  }

  @Override
  public ResponseObject<Bills> createBill(BillDto billDto) {
    try {
      objectMapper = new ObjectMapper();
      Optional<Users> user = userRepo.findById(billDto.getUserId());

      if (!user.isPresent()) {
        return new ResponseObject<Bills>("Không tìm thấy người dùng!", null);
      }

      Bills bill = new Bills();
      bill.setDate(billDto.getDate());
      bill.setStatus(billDto.getStatus());
      bill.setUserId(billDto.getUserId());
      bill.setListBooks(objectMapper.writeValueAsString(billDto.getListBooks()));
      bill.setUserInfo(objectMapper.writeValueAsString(billDto.getUserInfo()));

      ArrayList<Integer> cartIds = (ArrayList<Integer>) billDto.getListBooks().get("carts");
      for (Integer cartId: cartIds) {
        Carts c = new Carts();
        c.setStatus(0);
        c.setQuantity(this.getCart(cartId.longValue()).getQuantity());
        cartService.updateCart(c, cartId.longValue());
      }

      return new ResponseObject<>("Thanh toán thành công!", billRepo.save(bill));
    } catch (Exception e) {
      return new ResponseObject<Bills>(e.getMessage(), null);
    }
  }

  @Override
  public ResponseObject<Bills> getBill(Long id) {
    try {
      Optional<Bills> bill = billRepo.findById(id);

      if (!bill.isPresent()) {
        return new ResponseObject<Bills>("Hóa đơn không tồn tại!", null);
      }

      Map<String, Object> listBooks = objectMapper.readValue(bill.get().getListBooks(), Map.class);
      ArrayList<Carts> tmp = new ArrayList<>();
      bill.get().setInfo(objectMapper.readValue(bill.get().getUserInfo(), Map.class));

      ArrayList<Integer> cartIds = (ArrayList<Integer>) listBooks.get("carts");

      for (var cartId: cartIds) {
        tmp.add(this.getCart(cartId.longValue()));
      }
      bill.get().setListProducts(tmp);

      return new ResponseObject<Bills>(
          "Lấy ra hóa đơn thành công!!",
          bill.get()
      );
    } catch (Exception e) {
      return new ResponseObject<Bills>(
          e.getMessage(),
          null
      );
    }
  }

  @Override
  public ResponseObject<Bills> updateBill(Long id, BillDto updateBillDto) {
    try {
      Optional<Bills> bill = billRepo.findById(id);

      if (!bill.isPresent()) {
        return new ResponseObject<Bills>("Hóa đơn không tồn tại!", null);
      }

      Map<String, Object> listBooks = objectMapper.readValue(bill.get().getListBooks(), Map.class);
      ArrayList<Carts> tmp = new ArrayList<>();
      bill.get().setInfo(objectMapper.readValue(bill.get().getUserInfo(), Map.class));

      ArrayList<Integer> cartIds = (ArrayList<Integer>) listBooks.get("carts");

      for (var cartId: cartIds) {
        tmp.add(this.getCart(cartId.longValue()));
      }
      bill.get().setListProducts(tmp);
      bill.get().setStatus(updateBillDto.getStatus());

      return new ResponseObject<Bills>(
          "Lấy ra hóa đơn thành công!!",
          billRepo.save(bill.get())
      );
    } catch (Exception e) {
      return new ResponseObject<Bills>(
          e.getMessage(),
          null
      );
    }
  }

  public Carts getCart(Long id) {
    try {
      Optional<Carts> cart = cartRepo.findById(id);

      if (!cart.isPresent()) {
        return null;
      }

      Optional<Users> user = userRepo.findById(cart.get().getUserId());

      cart.get().setBooks(bookService.get(cart.get().getBookId()).getData());
      cart.get().setUser(user.get());

      return cart.get();
    } catch (Exception e) {
      return null;
    }
  }
}
