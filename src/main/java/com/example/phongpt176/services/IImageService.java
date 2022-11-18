package com.example.phongpt176.services;

import com.example.phongpt176.models.Images;
import com.example.phongpt176.models.ResponseObject;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
  ResponseObject<Images> upload(MultipartFile multipartFile, Long bookId);

  ResponseObject<Object> destroy(String publicId);

  ResponseObject<Object> destroyBookZero();

  ResponseObject<Images> updateBookId(Images imageBody, Long id);
}
