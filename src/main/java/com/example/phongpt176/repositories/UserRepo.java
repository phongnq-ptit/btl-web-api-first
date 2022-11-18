package com.example.phongpt176.repositories;

import com.example.phongpt176.models.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {
  Users findByEmailAndPassword(String email, String password);

  Users findByEmail(String email);
}
