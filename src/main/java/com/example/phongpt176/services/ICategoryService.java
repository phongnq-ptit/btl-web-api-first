package com.example.phongpt176.services;

import com.example.phongpt176.models.Categories;
import com.example.phongpt176.models.ResponseObject;
import java.util.List;

public interface ICategoryService {
  ResponseObject<List<Categories>> getAllCategories();
}
