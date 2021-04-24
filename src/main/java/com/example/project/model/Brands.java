package com.example.project.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Brands {
        @Getter
        @Setter
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "brandid")
        private long brandid;

     @Getter
     @Setter
     @Column(name = "brandname")
     private String brandname;



}
