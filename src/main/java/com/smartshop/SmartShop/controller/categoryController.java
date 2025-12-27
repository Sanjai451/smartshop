package com.smartshop.SmartShop.controller;
import com.smartshop.SmartShop.dto.categoryDTO;
import com.smartshop.SmartShop.model.Category;
import com.smartshop.SmartShop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class categoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Add Category
    @PostMapping
    public categoryDTO addCategory(@RequestBody categoryDTO dto) {
        Category category = new Category();
        category.setCatName(dto.getCatName());

        Category saved = categoryRepository.save(category);
        return convertToDTO(saved);
    }

    // All Categories
    @GetMapping
    public List<categoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Search by Category Name
    @GetMapping("/search")
    public List<categoryDTO> searchCategory(@RequestParam String name) {
        return categoryRepository.findByCatNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // GET : By ID
    @GetMapping("/{id}")
    public categoryDTO getById(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return convertToDTO(category);
    }

    // DTO Mapper
    private categoryDTO convertToDTO(Category category) {
        categoryDTO dto = new categoryDTO();
        dto.setCatId(category.getCatId());
        dto.setCatName(category.getCatName());
        return dto;
    }
}
