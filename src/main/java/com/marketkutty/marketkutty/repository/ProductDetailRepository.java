package com.marketkutty.marketkutty.repository;

import com.marketkutty.marketkutty.model.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    ProductDetail findByProductId(Long productId);
}
