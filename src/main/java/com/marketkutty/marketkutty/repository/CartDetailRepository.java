package com.marketkutty.marketkutty.repository;

import com.marketkutty.marketkutty.model.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    void deleteByProduct_id(Long id);
}
