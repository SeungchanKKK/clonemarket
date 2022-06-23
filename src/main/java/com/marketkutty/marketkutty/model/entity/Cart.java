package com.marketkutty.marketkutty.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Cart {
    /*
        1. 비회원 장바구니 담기 시 - 로컬 스토리지 저장
        2. 비회원 장바구니 담은 후 로그인 시
             - 기존 장바구니 DB에 데이터가 없을 경우: 로컬 스토리지 내용 DB에 저장, 로컬 스토리지 비우기
             - 기존 장바구니 DB에 데이터가 있을 경우: 기존 장바구니 + 로컬 스토리지 내용 합침
               -- 로컬 스토리지: A 상품이 1개 & 장바구니: B 상품이 1개 담겨 있을 경우
                  --- 장바구니 DB: A 상품 1개, B 상품 1개
                  --- 로컬 스토리지: empty
               -- 로컬 스토리지: A 상품 3개 & 장바구니: A 상품 2개 담겨 있을 경우
                  --- 장바구니 DB: A 상품 3개 (로컬 스토리지의 개수가 최신)
                  --- 로컬 스토리지: empty
        3. 로그인 후 장바구니 담기 시 - 로컬 스토리지 저장 X. 바로 DB에 저장
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CART_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name="USER_ID")
    @JsonBackReference(value="cart-user-fk")
    private User user;

    private int delivery_fee;

    @OneToMany(mappedBy = "cart")
    @JsonBackReference(value = "cartdetail-cart-fk")
    private List<CartDetail> cartDetailList= new ArrayList<>();

    @Builder
    public Cart(Long id, User user, int delivery_fee) {
        this.id = id;
        this.user = user;
        this.delivery_fee = delivery_fee;
    }

    public Cart(User user, CartDetail cartDetail) {
        this.user = user;
        this.delivery_fee = 7000;
        this.cartDetailList.add(cartDetail);
    }

    public Cart(User user){
        this.user = user;
        this.delivery_fee = 7000;
    }

    public void addCartDeatilList(CartDetail cartDetail) {
        this.cartDetailList.add(cartDetail);
    }
}
