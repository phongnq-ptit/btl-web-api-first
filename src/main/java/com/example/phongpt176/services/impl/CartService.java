package com.example.phongpt176.services.impl;

import com.example.phongpt176.models.Books;
import com.example.phongpt176.models.Carts;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.models.Users;
import com.example.phongpt176.repositories.CartRepo;
import com.example.phongpt176.repositories.UserRepo;
import com.example.phongpt176.services.ICartService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {
  @Autowired
  private CartRepo cartRepo;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private BookService bookService;

  @Override
  public ResponseObject<List<Carts>> getCartByUser(Long userId) {
    try {
      Optional<Users> user = userRepo.findById(userId);

      if (!user.isPresent()) {
        return new ResponseObject("Không tìm thấy người dùng!", null);
      }

      List<Carts> carts = cartRepo.findAllByUserIdAndStatus(userId, 1).stream().map(
          row -> {
            ResponseObject<Books> book = bookService.get(row.getBookId());
            row.setUser(user.get());
            row.setBooks(book.getData());
            return row;
          }
      ).collect(Collectors.toList());

      return new ResponseObject<List<Carts>>(
          "Lấy ra giỏ hàng của người dùng " + userId + " thành công!",
          carts
      );
    } catch (Exception e) {
      return new ResponseObject<List<Carts>>(
          e.getMessage(),
          null
      );
    }
  }

  @Override
  public ResponseObject<Carts> createCartByUser(Carts cart) {
    try {
      Optional<Users> user = userRepo.findById(cart.getUserId());

      if (!user.isPresent()) {
        return new ResponseObject("Không tìm thấy người dùng!", null);
      }

      ResponseObject<Books> book = bookService.get(cart.getBookId());

      if (book.getData() == null) {
        return new ResponseObject("Không tìm thấy  sản phẩm!", null);
      }

      Optional<Carts> existsCart = Optional.ofNullable(
          cartRepo.findByUserIdAndBookIdAndStatus(cart.getUserId(),
              cart.getBookId(), 1));

      if (existsCart.isPresent()) {
        return new ResponseObject("Sản phẩm đã này có trong giỏ hàng!", null);
      }

      Carts newCart = new Carts();
      newCart.setUserId(cart.getUserId());
      newCart.setBookId(cart.getBookId());
      newCart.setUser(user.get());
      newCart.setBooks(book.getData());
      newCart.setQuantity(cart.getQuantity());
      newCart.setStatus(1);

      newCart = cartRepo.save(newCart);

      return new ResponseObject<Carts>(
          "Thêm sản phẩm vào giỏ hàng thành công!!",
          newCart
      );
    } catch (Exception e) {
      return new ResponseObject<Carts>(
          e.getMessage(),
          null
      );
    }
  }

  @Override
  public ResponseObject<Carts> updateCart(Carts cartUpdate, Long id) {
    try {
      if (cartUpdate.getQuantity() < 1) {
        return new ResponseObject("Số lượng đặt hàng phải lớn hơn 0!", null);
      }

      Optional<Carts> cart = cartRepo.findById(id);

      if (!cart.isPresent()) {
        return new ResponseObject("Không tồn tại giỏ hàng này!!", null);
      }

      Optional<Users> user = userRepo.findById(cart.get().getUserId());

      cart.get().setQuantity(cartUpdate.getQuantity());
      cart.get().setUser(user.get());
      cart.get().setBooks(bookService.get(cart.get().getBookId()).getData());
      cart.get().setStatus(cartUpdate.getStatus());

      return new ResponseObject<Carts>(
          "Cập nhật giỏ hàng thành công",
          cartRepo.save(cart.get())
      );
    } catch (Exception e) {
      return new ResponseObject<Carts>(
          e.getMessage(),
          null
      );
    }
  }

  @Override
  public ResponseObject<Object> deleteCart(Long id) {
    try {
      Optional<Carts> cart = cartRepo.findById(id);

      if (!cart.isPresent()) {
        return new ResponseObject("Không tồn tại giỏ hàng này!!", null);
      }

      cartRepo.deleteById(cart.get().getId());

      return new ResponseObject<Object>(
          "Xóa thành công!",
          "No content!"
      );
    } catch (Exception e) {
      return new ResponseObject<Object>(
          e.getMessage(),
          null
      );
    }
  }
}
