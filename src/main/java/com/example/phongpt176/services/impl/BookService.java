package com.example.phongpt176.services.impl;

import com.example.phongpt176.models.Books;
import com.example.phongpt176.models.Categories;
import com.example.phongpt176.models.Images;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.repositories.BookRepo;
import com.example.phongpt176.repositories.CategoryRepo;
import com.example.phongpt176.repositories.ImageRepo;
import com.example.phongpt176.services.IBookService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

  @Autowired
  private BookRepo bookRepo;

  @Autowired
  private ImageRepo imageRepo;

  @Autowired
  private CategoryRepo categoryRepo;

  @Override
  public ResponseObject<List<Books>> getAll() {
    try {
      List<Books> books = bookRepo.findAll().stream().map(
          row -> {
            Images img = imageRepo.findByBookId(row.getId());
            Optional<Categories> category = categoryRepo.findById(row.getCategoryId());
            row.setImage(img);
            row.setCategory(category.isPresent() ? category.get() : null);
            return row;
          }
      ).collect(Collectors.toList());

      return new ResponseObject<List<Books>>("Lay thanh cong toan bo sach", books);
    } catch (Exception e) {
      return new ResponseObject<List<Books>>(e.getMessage(), null);
    }
  }

  @Override
  public ResponseObject<Books> get(Long id) {
    try {
      Optional<Books> book = bookRepo.findById(id);

      if (!book.isPresent()) {
        return new ResponseObject<Books>("Không tìm thấy sách!!", null);
      }

      Images img = imageRepo.findByBookId(book.get().getId());
      Optional<Categories> category = categoryRepo.findById(book.get().getCategoryId());

      book.get().setCategory(category.get());
      book.get().setImage(img);

      return new ResponseObject<Books>(
          "Lay ra 1 quyen sach thanh cong",
          book.get()
      );
    } catch (Exception e) {
      return new ResponseObject<Books>(e.getMessage(), null);
    }
  }

  @Override
  public ResponseObject<Books> create(Books newBook) {
    try {
      Optional<Categories> category = categoryRepo.findById(newBook.getCategoryId());
      if (!category.isPresent()) {
        return new ResponseObject<Books>("Thể loại không tồn tại!", null);
      }

      Optional<Books> checkTitle = Optional.ofNullable(bookRepo.findByTitle(newBook.getTitle()));
      if (checkTitle.isPresent()) {
        return new ResponseObject<Books>("Tiêu đề sách" + newBook.getTitle() + "đã tồn tại!", null);
      }

      Books book = bookRepo.save(newBook);
      book.setCategory(category.get());
      Optional<Images> img = Optional.ofNullable(imageRepo.findByBookId(book.getId()));
      if (img.isPresent()) {
        book.setImage(img.get());
      } else {
        book.setImage(null);
      }

      return new ResponseObject<Books>("Tạo sản phẩm thành công!", book);
    } catch (Exception e) {
      return new ResponseObject<Books>(e.getMessage(), null);
    }
  }

  @Override
  public ResponseObject<Books> update(Long id, Books bookUpdate) {
    try {
      Optional<Books> book = bookRepo.findById(id).map(
          row -> {
            row.setAuthor(bookUpdate.getAuthor());
            row.setDate(bookUpdate.getDate());
            row.setDescription(bookUpdate.getDescription());
            row.setPage(bookUpdate.getPage());
            row.setTitle(bookUpdate.getTitle());
            row.setCategoryId(bookUpdate.getCategoryId());
            return bookRepo.save(row);
          }
      );

      if (!book.isPresent()) {
        return new ResponseObject<Books>("Không tìm thấy id sách!", null);
      }

      Optional<Categories> category = categoryRepo.findById(bookUpdate.getCategoryId());

      if (!category.isPresent()) {
        return new ResponseObject<Books>("Thể loại sách không chính xác!", null);
      }

      Images img = imageRepo.findByBookId(id);
      book.get().setCategory(category.get());
      book.get().setImage(img);

      return new ResponseObject<Books>("Cập nhật sản phẩm thành công!", book.get());
    } catch (Exception e) {
      return new ResponseObject<Books>(e.getMessage(), null);
    }
  }

  @Override
  public ResponseObject<Object> remove(Long id) {
    try {
      Optional<Books> book = bookRepo.findById(id);

      if (!book.isPresent()) {
        return new ResponseObject<Object>("Không tìm thấy sách cần xóa!", null);
      }

      Images img = imageRepo.findByBookId(book.get().getId());
      if (img != null) {
        imageRepo.deleteById(img.getId());
      }
      bookRepo.deleteById(id);

      return new ResponseObject<Object>("Xóa sách thành công!", "No content!");
    } catch (Exception e) {
      return new ResponseObject<Object>(e.getMessage(), null);
    }
  }
}
