package com.irm.controller;

import com.irm.model.ProductVersion;
import com.irm.service.ProductVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-versions")
public class ProductVersionController {

    @Autowired
    private ProductVersionService versionService;

    @PostMapping("/product/{productId}")
    public ProductVersion createVersion(@PathVariable Long productId, @RequestBody ProductVersion versionRequest) {
        return versionService.createVersion(productId, versionRequest);
    }

    @GetMapping("/product/{productId}/latest")
    public ProductVersion getLatestVersion(@PathVariable Long productId) {
        return versionService.getLatestVersion(productId);
    }

    @GetMapping("/product/{productId}")
    public List<ProductVersion> getVersionsByProduct(@PathVariable Long productId) {
        return versionService.getVersionsByProductId(productId);
    }

    @GetMapping("/pending")
    public List<ProductVersion> getPendingVersions() {
        return versionService.getPendingVersions();
    }

    @PostMapping("/{versionId}/approve")
    public ProductVersion approveVersion(@PathVariable Long versionId) {
        return versionService.approveVersion(versionId);
    }

    @PostMapping("/{versionId}/reject")
    public ProductVersion rejectVersion(@PathVariable Long versionId) {
        return versionService.rejectVersion(versionId);
    }
}
