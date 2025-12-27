package com.smartshop.SmartShop.dto;

import lombok.Data;

import java.util.Map;

@Data
public class GeminiFunctionCall {
    private String method;
    private Map<String, Object> args;

    // getters & setters
}

