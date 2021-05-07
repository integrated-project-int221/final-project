package com.example.project.controller;

import com.example.project.exceptions.ExceptionResponse;
import com.example.project.exceptions.ProductException;
import com.example.project.model.Products;
import com.example.project.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/products")
//@CrossOrigin(origins= "http://localhost:8081")
public class ProductRestController {


    @Autowired
    private ProductRepositories productRepositories;


    @GetMapping("/items")
    public List<Products> getAllProduct() {
        return productRepositories.findAll();
    }



    @GetMapping("/item/{id}")
    public Products itemByID(@PathVariable Integer id) {
        Products product =productRepositories.findById(id).orElse(null);
        if(product==null) {
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST,
                    "Product does not exist error.");
        }return product;
    }

    @PostMapping("/add")
    public Products createProduct(@RequestBody Products newProduct){
        List<Products> productNameList = productRepositories.findByProdName(newProduct.getProdName());
        newProduct.setProdCode(10000);
        if(!productNameList.isEmpty()) {
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_ALREADY_EXIST,
                    "Duplicate name error.");
        }productRepositories.save(newProduct);
        return newProduct;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Integer id){
        Products product = productRepositories.findById(id).orElse(null);
        if(product==null){
            throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST, "Product does not exist.");
        }productRepositories.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void editProduct(@RequestBody Products putProduct,@PathVariable Integer id){
        Products p= productRepositories.findById(id).orElse(null);
        if(p==null){ throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST, "Product does not exist error."); };
        List<Products> products=productRepositories.findAll();
        List<Products> productStrems = products.stream().filter(prod -> prod.getProdCode() != id).collect(Collectors.toList());;
        for(Products prod : productStrems){
            if(prod.getProdName().equals(putProduct.getProdName())){
                throw new ProductException(ExceptionResponse.ERROR_CODE.ITEM_ALREADY_EXIST, "Duplicate name error.");}
        }
        productRepositories.findById(id).map(prod -> {
            prod.setProdName(putProduct.getProdName());
            prod.setBrands(putProduct.getBrands());
            prod.setProductColor(putProduct.getProductColor());
            prod.setPrice(putProduct.getPrice());
            prod.setProdDescription(putProduct.getProdDescription());
            prod.setProdManufactured(putProduct.getProdManufactured());
            return productRepositories.save(prod);
        });
    }
}
