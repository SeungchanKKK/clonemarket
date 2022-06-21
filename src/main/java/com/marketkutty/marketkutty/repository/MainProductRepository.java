package com.marketkutty.marketkutty.repository;

import com.marketkutty.marketkutty.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByDepth1_Id(String s);
}
