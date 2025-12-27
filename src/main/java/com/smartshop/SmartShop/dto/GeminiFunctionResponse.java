package com.smartshop.SmartShop.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;
@Data
public class GeminiFunctionResponse {

    @JsonProperty("function")
    private String functionName;

    @JsonProperty("arguments")
    private String argument;

    @JsonProperty("keyword")
    private String keyword;

    @JsonProperty("category")
    private String category;

    @JsonProperty("maxprice")
    private String maxprice;

    @JsonProperty("minprice")
    private String minprice;

    @Override
    public String toString() {
        return "GeminiFunctionResponse{" +
                "functionName='" + functionName + '\'' +
                ", key =" + keyword +
                '}';
    }
}
