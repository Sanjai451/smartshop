package com.smartshop.SmartShop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long cartId;

    private Double totalAmount;

    private String status;

    private LocalDateTime paymentTime = LocalDateTime.now();

    // getters & setters
}

