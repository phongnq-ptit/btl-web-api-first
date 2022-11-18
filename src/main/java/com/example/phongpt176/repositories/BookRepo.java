package com.example.phongpt176.repositories;

import com.example.phongpt176.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Books, Long> {

}
