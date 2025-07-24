package com.irm.service;

import com.irm.model.Product;
import com.irm.model.ProductVersion;
import com.irm.model.Status;
import com.irm.repository.ProductRepository;
import com.irm.repository.ProductVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductVersionService {

    @Autowired
    private ProductVersionRepository productVersionRepository;

    @Autowired
    private ProductRepository productRepository;

    // Create a new version for a product
    public ProductVersion createVersion(Long productId, ProductVersion versionRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        versionRequest.setProduct(product);
        versionRequest.setCreatedDate(LocalDateTime.now());
        versionRequest.setStatus(Status.PENDING);
        return productVersionRepository.save(versionRequest);
    }

    // Get latest approved version per product
    public ProductVersion getLatestVersion(Long productId) {
        List<ProductVersion> versions = productVersionRepository.findByProductId(productId);
        return versions.stream()
                .filter(v -> v.getStatus() == Status.APPROVED)
                .max(Comparator.comparing(ProductVersion::getEffectiveStartDate))
                .orElse(null);
    }

    // Get all versions by product
    public List<ProductVersion> getVersionsByProductId(Long productId) {
        return productVersionRepository.findByProductId(productId);
    }

    // Get all pending versions
    public List<ProductVersion> getPendingVersions() {
        return productVersionRepository.findByStatus(Status.PENDING);
    }

    // Approve version
    public ProductVersion approveVersion(Long versionId) {
        ProductVersion version = getVersionOrThrow(versionId);
        version.setStatus(Status.APPROVED);
        return productVersionRepository.save(version);
    }

    // Reject version
    public ProductVersion rejectVersion(Long versionId) {
        ProductVersion version = getVersionOrThrow(versionId);
        version.setStatus(Status.REJECTED);
        return productVersionRepository.save(version);
    }

    private ProductVersion getVersionOrThrow(Long versionId) {
        return productVersionRepository.findById(versionId)
                .orElseThrow(() -> new RuntimeException("Version not found"));
    }
}
