package com.smartshop.SmartShop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class SearchLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long searchId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String searchQuery;

    private LocalDateTime time = LocalDateTime.now();

    // getters & setters
}

