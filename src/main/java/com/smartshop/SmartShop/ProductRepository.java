package com.smartshop.SmartShop;

import com.smartshop.SmartShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Search by keyword (product name)
    @Query("""
        SELECT p FROM Product p
        WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Product> searchByKeyword(@Param("keyword") String keyword);


    // Search by keyword + max price
    @Query("""
        SELECT p FROM Product p
        WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))
        AND p.price <= :maxPrice
    """)
    List<Product> searchByKeywordAndMaxPrice(
            @Param("keyword") String keyword,
            @Param("maxPrice") Double maxPrice
    );


    // Search by category name
    @Query("""
        SELECT p FROM Product p
        WHERE LOWER(p.category.catName) = LOWER(:category)
    """)
    List<Product> searchByCategory(@Param("category") String category);


    //  Search by price range
    @Query("""
        SELECT p FROM Product p
        WHERE p.price BETWEEN :minPrice AND :maxPrice
    """)
    List<Product> searchByPriceRange(
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
}
