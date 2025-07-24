package com.irm.repository;

import com.irm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find all products ordered by productId ascending
    List<Product> findAllByOrderByProductIdAsc();

    // Find the latest version for a given productId
    Product findTopByProductIdOrderByVersionDesc(String productId);

    // Find all versions of a product by productId
    List<Product> findByProductIdOrderByVersionDesc(String productId);

    List<Product> findByStatus(String status);
    // Find all products ordered by createdAt descending
    List<Product> findAllByOrderByCreatedAtDesc();

    Product findTopByProductIdOrderByCreatedAtDesc(String productId);
}
