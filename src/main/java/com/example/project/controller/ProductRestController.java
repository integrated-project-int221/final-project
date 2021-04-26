package com.example.project.controller;

import com.example.project.model.Colors;
import com.example.project.model.Products;
import com.example.project.repositories.ColorRepositories;
import com.example.project.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductRestController {


    @Autowired
    private ProductRepositories productRepositories;


    @GetMapping("/items")
    public List<Products> getAllProduct() {
        return productRepositories.findAll();
    }



    @GetMapping("/items/{id}")
    public Products itemByID(@PathVariable Long id) {
        return productRepositories.findById(id).orElse(null);

    }


    @PostMapping("/add")
    public Products createProduct(@RequestBody Products newProduct){
         productRepositories.save(newProduct);
        return newProduct;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        productRepositories.deleteById(id);
    }


}
