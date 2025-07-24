package com.irm.controller;

import com.irm.model.Product;
import com.irm.service.JwtService;
import com.irm.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    JwtService jwtService;

    @PostMapping
    public Product createProduct(@RequestBody Product product, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7); // Remove "Bearer "
        String role = jwtService.extractRole(token); // assumes you have this method in JwtService
        return productService.createProduct(product, role);
    }

    @PostMapping("/update")
    public Product updateProductWithNewVersionAndUpdatedValues(@RequestBody Product product,  HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String role = jwtService.extractRole(token);
        return productService.updateProductWithNewVersionAndUpdatedValues(product,role);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @GetMapping("/latest")
    public List<Product> getLatestProducts() {
        return productService.getLatestProducts();
    }

    @GetMapping("/{productId}/versions")
    public List<Product> getAllVersions(@PathVariable String productId) {
        return productService.getAllVersions(productId);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/{id}/approve")
    public Product approveProduct(@PathVariable Long id) {
        return productService.approveProduct(id);
    }

    @PostMapping("/{id}/reject")
    public Product rejectProduct(@PathVariable Long id) {
        return productService.rejectProduct(id);
    }

}
