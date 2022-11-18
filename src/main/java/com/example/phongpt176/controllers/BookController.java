package com.example.phongpt176.controllers;

import com.example.phongpt176.models.Books;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.services.impl.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping()
  public ResponseEntity<ResponseObject<List<Books>>> getAll() {
    return ResponseEntity.ok().body(bookService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseObject<Books>> get(@PathVariable("id") Long id) {
    return ResponseEntity.ok().body(bookService.get(id));
  }

  @PostMapping()
  public ResponseEntity<ResponseObject<Books>> create(@RequestBody Books newBook) {
    return ResponseEntity.ok().body(bookService.create(newBook));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseObject<Books>> update(
      @RequestBody Books bookUpdate,
      @PathVariable Long id
  ) {
    return ResponseEntity.ok().body(bookService.update(id, bookUpdate));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseObject<Object>> delete(@PathVariable("id") Long id) {
    return ResponseEntity.ok().body(bookService.remove(id));
  }
}
