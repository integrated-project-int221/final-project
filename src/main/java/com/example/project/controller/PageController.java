package com.example.project.controller;

import com.example.project.model.Products;
import com.example.project.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/page")
@CrossOrigin(origins= "http://localhost:8081")
public class PageController {

    @Autowired
    ProductRepositories productRepositories;

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/productWithPage")
    public List<Products> productsWithPage(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "2") Integer pageSize
    ){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Products> pageResult = productRepositories.findAll(pageable);

        return pageResult.getContent();

    }

}
