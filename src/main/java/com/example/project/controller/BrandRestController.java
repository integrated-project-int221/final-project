package com.example.project.controller;


import com.example.project.model.Brands;
import com.example.project.repositories.BrandRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/brands")
@CrossOrigin(origins= "http://localhost:8081")
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
