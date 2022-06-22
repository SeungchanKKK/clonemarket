package com.marketkutty.marketkutty.repository;

import com.marketkutty.marketkutty.model.dto.CartDetailDto;
import com.marketkutty.marketkutty.model.dto.responseDto.CartRespDto;
import com.marketkutty.marketkutty.model.entity.Cart;
import com.marketkutty.marketkutty.model.entity.CartDetail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepository {
    @PersistenceContext
    EntityManager em;

    public CartRespDto findAllByUserId(Long userId){
        List<Cart> cart = em.createQuery("select c from Cart c where c.user.id=:userId", Cart.class)
                .setParameter("userId", userId)
                .getResultList();

        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();
        if(cart.size() > 0){

            for(CartDetail cartDetail:cart.get(0).getCartDetailList()){
                cartDetailDtoList.add(CartDetailDto.builder()
                                .id(cartDetail.getId())
                                .quantity(cartDetail.getQuantity())
                                .product(cartDetail.getProduct())
                        .build());
            }

            return CartRespDto.builder()
                    .result(true)
                    .id(cart.get(0).getId())
                    .delivery_fee(cart.get(0).getDelivery_fee())
                    .cartDetailList(cartDetailDtoList)
                    .responseMsg("장바구니 조회에 성공했습니다.")
                    .build();
        } else {
            return CartRespDto.builder()
                    .result(false)
                    .responseMsg("장바구니가 비었습니다.")
                    .build();
        }
    }
}
