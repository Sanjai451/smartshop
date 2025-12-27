package com.smartshop.SmartShop.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class productDTO {

    private Long productId;

    @NotBlank(message = "Product name must not be empty")
    private String productName;

    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer prodQuantity;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
}