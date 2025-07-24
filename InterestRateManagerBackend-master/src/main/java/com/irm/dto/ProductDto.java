package com.irm.dto;

import com.irm.model.Product;
import com.irm.model.ProductVersion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Product product;
    private ProductVersion latestVersion;
}
