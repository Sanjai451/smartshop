package com.smartshop.SmartShop.repository;

import com.smartshop.SmartShop.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 🔍 Search by product name (case-insensitive)
    @Query("""
           SELECT p FROM Product p
           WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))
           """)
    List<Product> findByProductName(@Param("name") String name);

    // 📦 Products by category ID
    @Query("""
           SELECT p FROM Product p
           WHERE p.category.catId = :catId
           """)
    List<Product> findProductsByCategoryId(@Param("catId") Long catId);

    // ✅ Check existence by name
    @Query("""
           SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END
           FROM Product p
           WHERE LOWER(p.productName) = LOWER(:name)
           """)
    boolean existsByProductNameIgnoreCase(@Param("name") String name);

    // 🗑 Delete by product name
    @Modifying
    @Transactional
    @Query("""
           DELETE FROM Product p
           WHERE LOWER(p.productName) = LOWER(:name)
           """)
    void deleteByProductNameIgnoreCase(@Param("name") String name);
}
