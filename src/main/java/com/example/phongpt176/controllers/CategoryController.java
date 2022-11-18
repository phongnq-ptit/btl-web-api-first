package com.example.phongpt176.controllers;

import com.example.phongpt176.models.Categories;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.services.impl.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<ResponseObject<List<Categories>>> getAll() {
    return ResponseEntity.ok().body(categoryService.getAllCategories());
  }
}
