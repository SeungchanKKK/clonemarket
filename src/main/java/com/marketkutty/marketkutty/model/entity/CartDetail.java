package com.marketkutty.marketkutty.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="CART_ID")
    @JsonBackReference(value = "cartdetail-cart-fk")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    @JsonBackReference(value = "cart-product-fk")
    private Product product;

    private int quantity;
}
