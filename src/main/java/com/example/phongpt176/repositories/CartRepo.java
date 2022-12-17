package com.example.phongpt176.repositories;

import com.example.phongpt176.models.Carts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Carts, Long> {
  List<Carts> findAllByUserIdAndStatus(Long userId, int status);

  Carts findByUserIdAndBookIdAndStatus(Long userId, Long bookId, int status);
}
