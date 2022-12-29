package com.example.phongpt176.services.impl;

import com.example.phongpt176.models.Books;
import com.example.phongpt176.models.Categories;
import com.example.phongpt176.models.Comments;
import com.example.phongpt176.models.Images;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.models.Users;
import com.example.phongpt176.models.dto.CommentDto;
import com.example.phongpt176.repositories.CommentRepo;
import com.example.phongpt176.repositories.UserRepo;
import com.example.phongpt176.services.ICommentService;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

  @Autowired
  private CommentRepo commentRepo;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private BookService bookService;

  @Override
  public ResponseObject<ArrayList<CommentDto>> getAllCommentOfBook(Long bookId) {
    try {
      ResponseObject<Books> book = bookService.get(bookId);

      if (book.getData() == null) {
        return new ResponseObject<>("Không tìm thấy Id của sách", null);
      }

      ArrayList<CommentDto> result = new ArrayList<>();
      commentRepo.findAllByBookId(bookId).stream().map(
          row -> {
            Optional<Users> user = userRepo.findById(row.getUserId());

            CommentDto comment = new CommentDto();
            comment.setId(row.getId());
            comment.setBook(book.getData());
            comment.setRate(row.getRate());
            comment.setComment(row.getComment());
            comment.setUser(user.get());
            comment.setDate(row.getDate());

            result.add(comment);
            return row;
          }
      ).collect(Collectors.toList());

      return new ResponseObject<ArrayList<CommentDto>>(
          "Lấy thành công toàn bộ comment của sản phẩm!", result);
    } catch (Exception e) {
      return new ResponseObject<ArrayList<CommentDto>>(e.getMessage(), null);
    }
  }

  @Override
  public ResponseObject<CommentDto> createComment(Comments comment) {
    try {
      Optional<Users> user = userRepo.findById(comment.getUserId());
      if (!user.isPresent()) {
        return new ResponseObject<CommentDto>("Không tồn tại user này!", null);
      }

      Books book = bookService.get(comment.getBookId()).getData();
      if (book == null) {
        return new ResponseObject<CommentDto>("Không tồn tại sản phẩm này!", null);
      }

      CommentDto commentDto = new CommentDto();
      commentDto.setUser(user.get());
      commentDto.setRate(comment.getRate());
      commentDto.setComment(comment.getComment());
      commentDto.setBook(book);
      commentDto.setDate(comment.getDate());

      commentRepo.save(comment);

      return new ResponseObject<CommentDto>("Tạo sản phẩm thành công!", commentDto);
    } catch (Exception e) {
      return new ResponseObject<CommentDto>(e.getMessage(), null);
    }
  }
}
