package com.marketkutty.marketkutty.repository;

import com.marketkutty.marketkutty.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartPostRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findCartByUser_Id(Long userId);

}
