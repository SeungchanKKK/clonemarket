package com.marketkutty.marketkutty.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
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
    @JsonBackReference(value = "cartdetail-product-fk")
    private Product product;

    private int quantity;

    public void updateQuantity(int quantity){
        this.quantity = quantity;
    }
}
