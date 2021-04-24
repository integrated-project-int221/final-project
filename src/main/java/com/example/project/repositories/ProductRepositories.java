package com.example.project.repositories;

import com.example.project.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositories extends JpaRepository<Products, Long> {

}
