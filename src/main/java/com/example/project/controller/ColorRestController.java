package com.example.project.controller;

import com.example.project.model.Colors;
import com.example.project.repositories.ColorRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/colors")
public class ColorRestController {
    @Autowired
    private ColorRepositories colorRepositories;

    @GetMapping("")
    public List<Colors> getAllColorProduct() {
        return colorRepositories.findAll();
    }

    @GetMapping("/{id}")
    public Colors colorByID(@PathVariable Long id) {
        return colorRepositories.findById(id).orElse(null);
    }


}
