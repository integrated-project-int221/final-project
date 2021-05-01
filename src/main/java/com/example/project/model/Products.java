package com.example.project.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prodcode", updatable = false,nullable = false)
    private Integer prodCode;

    @ManyToOne
    @JoinColumn(name = "bands_bandid", nullable = false)
    private Brands brands;


    @Column(name = "prodname")
    private String prodName;


    @Column(name = "proddescription")
    private String prodDescription;


    @Column(name = "price")
    private double price;


    @Column(name = "prodmanufactured")
    private LocalDate prodManufactured;

    @Column(name = "imagename")
    private String imageName;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "productcolor",
            joinColumns = @JoinColumn(name = "prodcode"),
            inverseJoinColumns = @JoinColumn(name = "colorid")
    )
    private List<Colors> productColor = new ArrayList<>();
}
