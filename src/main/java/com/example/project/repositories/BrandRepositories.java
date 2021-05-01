package com.example.project.repositories;

import com.example.project.model.Brands;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepositories extends JpaRepository<Brands,Integer> {

}
