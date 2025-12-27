package com.smartshop.SmartShop.controller;

import com.smartshop.SmartShop.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class QuerySearchController {

    @Autowired
    ProductSearchService productSearchService;

    @GetMapping("/")
    public ResponseEntity<?> help(){
        return new ResponseEntity<>(Map.of("message", "hello World"), HttpStatus.OK);
    }

    @GetMapping("/searchbyquery/{query}")
    public ResponseEntity<?> searchProductUsingQuery(@PathVariable String query){
        System.out.println(query);
        try{
            return new ResponseEntity<>(productSearchService.search(query), HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(Map.of("message", "No Data Found"), HttpStatus.FORBIDDEN);
        }
    }
}
