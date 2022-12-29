package com.example.phongpt176.controllers;

import com.example.phongpt176.models.Comments;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.models.dto.CommentDto;
import com.example.phongpt176.services.impl.CommentService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/comments")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @GetMapping()
  public ResponseEntity<ResponseObject<ArrayList<CommentDto>>> getAllCommentOfBook(
      @RequestParam(name = "bookId") String bookId) {
    return ResponseEntity.ok().body(commentService.getAllCommentOfBook(Long.parseLong(bookId)));
  }

  @PostMapping()
  public ResponseEntity<ResponseObject<CommentDto>> createComment(@RequestBody Comments comment) {
    return ResponseEntity.ok().body(commentService.createComment(comment));
  }
}
