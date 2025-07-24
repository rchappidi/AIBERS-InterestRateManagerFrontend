package com.irm.service;

import com.irm.model.Product;
import com.irm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Save new product version
    public Product createProduct(Product product, String role) {
        int nextVersion = 1;
        List<Product> existingVersions = productRepository.findByProductIdOrderByVersionDesc(product.getProductId());

        if (!existingVersions.isEmpty()) {
            nextVersion = existingVersions.get(0).getVersion() + 1;
        }

        product.setVersion(nextVersion);
        product.setCreatedAt(LocalDateTime.now());
        if ("CHECKER".equalsIgnoreCase(role)) {
            product.setStatus("Active");
        } else {
            product.setStatus("InActive - Pending for Approval");
        }

        // Optional: default future effectiveStartDate if not provided
        if (product.getEffectiveStartDate() == null) {
            product.setEffectiveStartDate(LocalDateTime.now().plusDays(1));
        }

        return productRepository.save(product);
    }

    // Get latest version of each productId
    public List<Product> getLatestProducts() {
        List<Product> all = productRepository.findAllByOrderByProductIdAsc();

        Map<String, Product> latestMap = all.stream()
                .collect(Collectors.toMap(
                        Product::getProductId,
                        p -> p,
                        (p1, p2) -> p1.getVersion() > p2.getVersion() ? p1 : p2
                ));

        return latestMap.values().stream().toList();
    }

    // Get all versions of a product
    public List<Product> getAllVersions(String productId) {
        return productRepository.findByProductIdOrderByVersionDesc(productId);
    }

    // Get all products (all versions)
    public List<Product> getAllProducts() {
        return productRepository.findAllByOrderByCreatedAtDesc();
    }

    // Update product directly (used in edit flow)
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        existing.setProductName(updatedProduct.getProductName());
        existing.setProductDescription(updatedProduct.getProductDescription());
        existing.setInterestRate(updatedProduct.getInterestRate());
        existing.setEffectiveStartDate(updatedProduct.getEffectiveStartDate());
        existing.setStatus(updatedProduct.getStatus());
        return productRepository.save(existing);
    }

    public Product getLatestProductByProductId(String productId) {
        return productRepository.findTopByProductIdOrderByVersionDesc(productId);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product updateProductWithNewVersionAndUpdatedValues(Product product, String role) {


            // Ensure we create a new product record
            product.setId(null);  // Force new insert

            if (product.getProductId() != null && product.getVersion() != null) {
                Product latestProduct = productRepository.findTopByProductIdOrderByCreatedAtDesc(product.getProductId());
                product.setVersion(latestProduct.getVersion() + 1);
            } else {
                // In case of new product creation
                product.setVersion(1);
            }

            if ("CHECKER".equalsIgnoreCase(role)) {
                product.setStatus("Active");
            } else {
                product.setStatus("InActive - Pending for Approval");
            }

            return productRepository.save(product);
    }

    public Product approveProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        product.setStatus("Active");
        return productRepository.save(product);
    }

    public Product rejectProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        product.setStatus("Inactive - Rejected");
        return productRepository.save(product);
    }

}
