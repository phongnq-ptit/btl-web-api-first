package com.example.phongpt176.services.impl;

import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.models.Users;
import com.example.phongpt176.repositories.UserRepo;
import com.example.phongpt176.services.IUserService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  @Autowired
  private UserRepo userRepo;

  // Ma hoa mat khau voi MD5
  public String hashPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(password.getBytes());
    byte[] digest = md.digest();
    String myHash = DatatypeConverter
        .printHexBinary(digest);
    return myHash;
  }

  @Override
  public ResponseObject<Users> login(Users user) {
    try {
      user.setPassword(hashPassword(user.getPassword()));
      Optional<Users> _user = Optional.ofNullable(
          userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword()));

      if (!_user.isPresent()) {
        return new ResponseObject<Users>("Tài khoản hoặc mật khẩu không chính xác!", null);
      }

      return new ResponseObject<Users>("Đăng nhập thành công!", _user.get());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseObject<Users> register(Users user) {
    try {
      Optional<Users> checkEmail = Optional.ofNullable(userRepo.findByEmail(user.getEmail()));

      if (checkEmail.isPresent()) {
        return new ResponseObject<Users>("Email đã tồn tại!", null);
      }

      user.setPassword(hashPassword(user.getPassword()));

      return new ResponseObject<Users>(
          "Đăng ký thành công!",
          userRepo.save(user)
      );
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}
