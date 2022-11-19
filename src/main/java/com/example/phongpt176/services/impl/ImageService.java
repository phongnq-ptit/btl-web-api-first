package com.example.phongpt176.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.phongpt176.models.Books;
import com.example.phongpt176.models.Images;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.repositories.ImageRepo;
import com.example.phongpt176.services.IImageService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService implements IImageService {

  @Autowired
  private ImageRepo imageRepo;

  Cloudinary cloudinary;

  private Map<String, String> valuesMap = new HashMap<>();

  public ImageService() {
    valuesMap.put("cloud_name", "dicisqibf");
    valuesMap.put("api_key", "486944772924843");
    valuesMap.put("api_secret", "GxSnuaFCedQfx8pHaFDzYMkODqw");
    cloudinary = new Cloudinary(valuesMap);
  }

  private File convert(MultipartFile multipartFile) throws IOException {
    File file = new File(multipartFile.getOriginalFilename());
    FileOutputStream fo = new FileOutputStream(file);
    fo.write(multipartFile.getBytes());
    fo.close();
    return file;
  }

  @Override
  public ResponseObject<Images> upload(MultipartFile multipartFile, Long bookId) {
    try {
      if (bookId == -1) {
        return new ResponseObject<Images>("Không có sách nào được chọn", null);
      }

      if (multipartFile.isEmpty()) {
        return new ResponseObject<Images>("Không có ảnh nào được tải lên", null);
      }

      File file = null;
      file = convert(multipartFile);

      if (file.length() > 1024 * 1024) {
        return new ResponseObject<Images>("Kích thước ảnh lớn hơn 1Mb", null);
      }

      String mimeType = FilenameUtils.getExtension(file.getPath());
      if (!mimeType.equals("jpg") && !mimeType.equals("png") && !mimeType.equals("jpeg")) {
        return new ResponseObject<Images>("Ảnh không đúng định dạng!", null);
      }

      Map img = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "ltw"));

      Images newImage = new Images();
      newImage.setUrl(img.get("secure_url").toString());
      newImage.setPublicId(img.get("public_id").toString().split("/")[1]);
      newImage.setBookId(bookId);

      return new ResponseObject<Images>("Tải ảnh lên thành công!", imageRepo.save(newImage));
    } catch (Exception e) {
      return new ResponseObject<Images>(e.getMessage(), null);
    }
  }

  @Override
  public ResponseObject<Object> destroy(String publicId) {
    try {
      Optional<Images> image = Optional.ofNullable(
          imageRepo.findByPublicId(publicId));

      if (!image.isPresent()) {
        return new ResponseObject<Object>("Ảnh không tồn tại!", null);
      }

      Map result = cloudinary.uploader().destroy("ltw/" + publicId, ObjectUtils.emptyMap());

      if (!result.get("result").toString().equalsIgnoreCase("ok")) {
        return new ResponseObject<Object>("Xóa ảnh không thành công!", null);
      }

      imageRepo.deleteById(image.get().getId());

      return new ResponseObject<Object>("Xóa ảnh thành công!", "No content");
    } catch (Exception e) {
      return new ResponseObject<Object>(e.getMessage(), null);
    }
  }

  @Override
  public ResponseObject<Object> destroyBookZero() {
    try {
      Optional<Images> img = Optional.ofNullable(imageRepo.findByBookId(Long.valueOf(0)));

      if (!img.isPresent()) {
        return new ResponseObject<Object>("Không có bookId 0", "No content");
      }

      imageRepo.deleteById(img.get().getId());

      Map result = cloudinary.uploader().destroy("ltw/" + img.get().getPublicId(), ObjectUtils.emptyMap());

      if (!result.get("result").toString().equalsIgnoreCase("ok")) {
        return new ResponseObject<Object>("Xóa ảnh không thành công!", null);
      }

      return new ResponseObject<Object>("Xóa ảnh có bookId 0 thành công!", "No content");
    } catch (Exception e) {
      return new ResponseObject<Object>(e.getMessage(), null);
    }
  }

  @Override
  public ResponseObject<Images> updateBookId(Images imageBody, Long id) {
    try {
      Optional<Images> img = imageRepo.findById(id).map(row -> {
        row.setBookId(imageBody.getBookId());
        return imageRepo.save(row);
      });

      if (!img.isPresent()) {
        return new ResponseObject<Images>("Không tồn tại id này", null);
      }

      return new ResponseObject<Images>("Cập nhật thành công!", img.get());
    } catch (Exception e) {
      return new ResponseObject<Images>(e.getMessage(), null);
    }
  }


}
