
package com.irm.repository;

import com.irm.model.ProductVersion;
import com.irm.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVersionRepository extends JpaRepository<ProductVersion, Long> {

    List<ProductVersion> findByProductId(Long productId);
    List<ProductVersion> findByStatus(Status status);
}
