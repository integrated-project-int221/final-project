package com.example.project.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Products {
    @Getter
    @Setter
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prodcode")
    private long prodcode;

    @ManyToOne
    @JoinColumn(name = "bands_bandid", nullable = false)
    @Getter
    @Setter
    private Brands brands;

    @Getter
    @Setter
    @Column(name = "prodname")
    private String prodname;

    @Getter
    @Setter
    @Column(name = "proddescription")
    private String proddescription;

    @Getter
    @Setter
    @Column(name = "price")
    private double price;

    @Getter
    @Setter
    @Column(name = "prodmanufactured")
    private LocalDate prodmanufactured;

    @Getter
    @Setter
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
