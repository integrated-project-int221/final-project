package com.example.project.controller;

import com.example.project.model.Products;
import com.example.project.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
@CrossOrigin(origins= "http://localhost:8081")
public class ProductRestController {


    @Autowired
    private ProductRepositories productRepositories;


    @GetMapping("/items")
    public List<Products> getAllProduct() {
        return productRepositories.findAll();
    }



    @GetMapping("/items/{id}")
    public Products itemByID(@PathVariable Integer id) {
        return productRepositories.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public Products createProduct(@RequestBody Products newProduct){
        newProduct.setProdCode(1000);
         productRepositories.save(newProduct);
        return newProduct;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Integer id){
        productRepositories.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void editProduct(@RequestBody Products putProduct,@PathVariable Integer id){
        productRepositories.findById(id).map(products -> {
            products.setProdName(putProduct.getProdName());
            products.setBrands(putProduct.getBrands());
            products.setProductColor(putProduct.getProductColor());
            products.setPrice(putProduct.getPrice());
            products.setProdDescription(putProduct.getProdDescription());
            products.setProdManufactured(putProduct.getProdManufactured());
            return productRepositories.save(products);
        });
    }
}
