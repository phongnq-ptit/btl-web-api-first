package com.example.phongpt176.repositories;

import com.example.phongpt176.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Categories, Long> {

}
