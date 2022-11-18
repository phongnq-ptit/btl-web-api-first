package com.example.phongpt176.services;

import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.models.Users;

public interface IUserService {
  ResponseObject<Users> login(Users user);
  ResponseObject<Users> register(Users user);
}
