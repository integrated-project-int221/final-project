package com.example.project.controller;

import com.example.project.model.Colors;
import com.example.project.repositories.ColorRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/colors")
@CrossOrigin(origins= {"http://localhost:8081"})
public class ColorRestController {
    @Autowired
    private ColorRepositories colorRepositories;

    @GetMapping("")
    public List<Colors> getAllColorProduct() {
        return colorRepositories.findAll();
    }

    @GetMapping("/{id}")
    public Colors colorByID(@PathVariable Integer id) {
        return colorRepositories.findById(id).orElse(null);
    }


}
