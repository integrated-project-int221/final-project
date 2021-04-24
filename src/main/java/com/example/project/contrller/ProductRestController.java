package com.example.project.contrller;
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
    private ColorRepositories colorRepositories;

    @Autowired
    private ProductRepositories productRepositories;



    @GetMapping("/items")
    public List<Products> getAllProduct(){
        return productRepositories.findAll();
    }

    @GetMapping("/colors")
    public  List<Colors> getAllColorProduct(){
        return colorRepositories.findAll();
    }

    @GetMapping("/items/{id}")
    public Products itemByID(@PathVariable Long id){
        return productRepositories.findById(id).orElse(null);
    }

    @GetMapping("/colors/{id}")
    public Colors ColorByID(@PathVariable Long id){
        return colorRepositories.findById(id).orElse(null);
    }


}
