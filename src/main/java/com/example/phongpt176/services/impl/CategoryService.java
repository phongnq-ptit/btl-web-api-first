package com.example.phongpt176.services.impl;

import com.example.phongpt176.models.Categories;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.repositories.CategoryRepo;
import com.example.phongpt176.services.ICategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {
  @Autowired
  private CategoryRepo categoryRepo;

  @Override
  public ResponseObject<List<Categories>> getAllCategories() {
    try {
      return new ResponseObject<List<Categories>>("Lay ra ta ca cac the loai sach", categoryRepo.findAll());
    } catch (Exception e) {
      return new ResponseObject<List<Categories>>(e.getMessage(), null);
    }
  }
}
