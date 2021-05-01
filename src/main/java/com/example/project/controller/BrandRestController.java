package com.example.project.controller;


import com.example.project.model.Brands;
import com.example.project.repositories.BrandRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandRestController {
    @Autowired
    private BrandRepositories brandRepositories;

    @GetMapping("")
    public List<Brands> getAllBrand() {

        return brandRepositories.findAll();
    }

    @GetMapping("/{id}")
    public Brands brandByID(@PathVariable Integer id) {
        return brandRepositories.findById(id).orElse(null);
    }

}
