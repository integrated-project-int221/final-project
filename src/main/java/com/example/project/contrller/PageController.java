package com.example.project.contrller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class PageController {

    @GetMapping("/")
    public String getHome(){
        return "Test Deploy SpringBoot RestAPI";
    }

}
