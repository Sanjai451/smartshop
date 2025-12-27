package com.smartshop.SmartShop.controller;

import com.smartshop.SmartShop.dto.productDTO;
import com.smartshop.SmartShop.exception.ResourceNotFoundException;
import com.smartshop.SmartShop.model.Category;
import com.smartshop.SmartShop.model.Product;
import com.smartshop.SmartShop.repository.CategoryRepository;
import com.smartshop.SmartShop.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class productController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // ADD PRODUCT
    @PostMapping(consumes = "application/json")
    public productDTO addProduct(@Valid @RequestBody productDTO dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found with id " + dto.getCategoryId()));

        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setProdQuantity(dto.getProdQuantity());
        product.setCategory(category);

        return convertToDTO(productRepository.save(product));
    }

    // GET ALL PRODUCTS
    @GetMapping
    public List<productDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public productDTO getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id " + id));
        return convertToDTO(product);
    }

    // SEARCH PRODUCT BY NAME
    @GetMapping("/search")
    public List<productDTO> getProductByName(@RequestParam String name) {
        return productRepository.findByProductName(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // GET PRODUCTS BY CATEGORY ID
    @GetMapping("/category/{catId}")
    public List<productDTO> getProductsByCategoryId(@PathVariable Long catId) {

        return productRepository.findProductsByCategoryId(catId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // DELETE PRODUCT BY ID
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id " + id));

        productRepository.delete(product);
        return "Product deleted successfully";
    }


    // DTO MAPPER
    private productDTO convertToDTO(Product product) {
        productDTO dto = new productDTO();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setProdQuantity(product.getProdQuantity());
        dto.setCategoryId(product.getCategory().getCatId());
        return dto;
    }
}