package com.example.phongpt176.services;

import com.example.phongpt176.models.Books;
import com.example.phongpt176.models.ResponseObject;
import java.util.List;

public interface IBookService {

  ResponseObject<List<Books>> getAll();

  ResponseObject<Books> get(Long id);

  ResponseObject<Books> create(Books newBook);

  ResponseObject<Books> update(Long id, Books bookUpdate);

  ResponseObject<Object> remove(Long id);
}
