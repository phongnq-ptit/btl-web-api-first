package com.example.phongpt176.repositories;

import com.example.phongpt176.models.Bills;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bills, Long> {
  ArrayList<Bills> findAllByUserId(Long userId);
}
