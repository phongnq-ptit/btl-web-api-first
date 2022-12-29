package com.example.phongpt176.services;

import com.example.phongpt176.models.Comments;
import com.example.phongpt176.models.ResponseObject;
import com.example.phongpt176.models.dto.CommentDto;
import java.util.ArrayList;

public interface ICommentService {
  ResponseObject<ArrayList<CommentDto>> getAllCommentOfBook(Long bookId);

  ResponseObject<CommentDto> createComment(Comments comment);
}
