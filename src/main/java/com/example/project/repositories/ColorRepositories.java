package com.example.project.repositories;

import com.example.project.model.Colors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepositories extends JpaRepository<Colors, Integer> {

}
