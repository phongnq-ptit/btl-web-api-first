package com.example.phongpt176.repositories;

import com.example.phongpt176.models.Images;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Images, Long> {

  Images findByBookId(Long bookId);

  Images findByPublicId(String publicId);
}
