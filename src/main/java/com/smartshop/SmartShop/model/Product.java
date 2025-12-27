package com.smartshop.SmartShop.model;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String description;

    private Double price;

    private Integer prodQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // getters & setters
}
