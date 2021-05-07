package com.example.project.repositories;

import com.example.project.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepositories extends JpaRepository<Products, Integer> {
    List<Products> findByProdName(String prodname);

}
