package com.marketkutty.marketkutty.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
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

    @Builder
    public CartDetail(Long id, Cart cart, Product product, int quantity) {
        this.id = id;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public CartDetail(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public void plusnum(int s){
        this.quantity = s;
    }

  //plusnum 과 합치기
    public void updateQuantity(CartDetail cartDetail, int quantity){
        cartDetail.quantity = quantity;
    }

}
