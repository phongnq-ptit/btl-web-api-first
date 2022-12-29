package com.example.phongpt176.repositories;

import com.example.phongpt176.models.Comments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comments, Long> {
  List<Comments> findAllByBookId(Long bookId);
}
