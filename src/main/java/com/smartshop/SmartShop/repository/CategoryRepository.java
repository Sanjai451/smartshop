package com.smartshop.SmartShop.repository;

import com.smartshop.SmartShop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // search by category name
    List<Category> findByCatNameContainingIgnoreCase(String catName);
}
