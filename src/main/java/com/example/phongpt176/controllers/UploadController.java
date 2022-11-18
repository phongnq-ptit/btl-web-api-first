package com.example.phongpt176.controllers;

import com.example.phongpt176.models.Images;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.services.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/image")
public class UploadController {

  @Autowired
  private ImageService imageService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseObject<Images>> upload(
      @RequestParam MultipartFile file,
      @RequestParam(name = "bookId", defaultValue = "-1") String bookId
  ) {
    return ResponseEntity.ok().body(imageService.upload(file, Long.parseLong(bookId)));
  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<ResponseObject<Images>> update(@RequestBody Images imgBody, @PathVariable("id") Long id) {
    return ResponseEntity.ok().body(imageService.updateBookId(imgBody, id));
  }

  @DeleteMapping("/destroy")
  public ResponseEntity<ResponseObject<Object>> destroyBookZero() {
    return ResponseEntity.ok().body(imageService.destroyBookZero());
  }

  @DeleteMapping("/destroy/{publicId}")
  public ResponseEntity<ResponseObject<Object>> destroy(@PathVariable("publicId") String publicId) {
    return ResponseEntity.ok().body(imageService.destroy(publicId));
  }
}
