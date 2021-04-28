package com.example.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Colors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="colorid" , updatable = false,nullable = false)
    private Integer colorId;

    @Column(name = "colorname")
    private String colorName;

    @Column(name= "colorvalue")
    private String colorValue;


}
