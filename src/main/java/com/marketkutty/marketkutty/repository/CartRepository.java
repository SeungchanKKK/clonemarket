package com.marketkutty.marketkutty.repository;

import com.marketkutty.marketkutty.model.dto.requestDto.CartMergeDto;
import com.marketkutty.marketkutty.model.dto.requestDto.CartUpdateDto;
import com.marketkutty.marketkutty.model.dto.responseDto.CartByTypeDto;
import com.marketkutty.marketkutty.model.dto.responseDto.CartDataDto;
import com.marketkutty.marketkutty.model.dto.responseDto.CartRespDto;
import com.marketkutty.marketkutty.model.entity.Cart;
import com.marketkutty.marketkutty.model.entity.CartDetail;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CartRepository {
    @PersistenceContext
    EntityManager em;

    public void saveCart(Cart cart){
        em.persist(cart);
    }

    public void saveCartDetail(CartDetail cartDetail) {
        em.persist(cartDetail);
    }

    public List<Cart> findCartByUser(Long userId) {
        return em.createQuery("select c from Cart c where c.user.id=:userId", Cart.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<CartDetail> findCartDetail(List<Cart> cart) {
        List<CartDetail> cartDetailList = em.createQuery
                        ("select d from CartDetail d where d.cart.id=:cartId", CartDetail.class)
                .setParameter("cartId", cart.get(0).getId())
                .getResultList();
        return cartDetailList;
    }

    public CartRespDto getCart(Long userId){
        List<Cart> cart = findCartByUser(userId);
        CartRespDto cartRespDto;

        if(cart.size() > 0) {
            List<CartDetail> cartDetailList = findCartDetail(cart);

            List<CartByTypeDto> cartByTypeList = new ArrayList<>();
            List<CartDataDto> normalData = new ArrayList<>();
            List<CartDataDto> coldData = new ArrayList<>();
            List<CartDataDto> frozenData = new ArrayList<>();

            for (CartDetail cartDetail : cartDetailList) {
                switch (cartDetail.getProduct().getPackageType()) {
                    case "??????":
                        normalData.add(CartDataDto.builder()
                                .productId(cartDetail.getProduct().getId())
                                .name(cartDetail.getProduct().getName())
                                .thumb(cartDetail.getProduct().getThumb())
                                .price(cartDetail.getProduct().getPrice())
                                .quantity(cartDetail.getQuantity())
                                .build());
                        break;
                    case "??????":
                        coldData.add(CartDataDto.builder()
                                .productId(cartDetail.getProduct().getId())
                                .name(cartDetail.getProduct().getName())
                                .thumb(cartDetail.getProduct().getThumb())
                                .price(cartDetail.getProduct().getPrice())
                                .quantity(cartDetail.getQuantity())
                                .build());
                        break;
                    case "??????":
                        frozenData.add(CartDataDto.builder()
                                .productId(cartDetail.getProduct().getId())
                                .name(cartDetail.getProduct().getName())
                                .thumb(cartDetail.getProduct().getThumb())
                                .price(cartDetail.getProduct().getPrice())
                                .quantity(cartDetail.getQuantity())
                                .build());
                        break;
                }
            }

            if(normalData.isEmpty()) {
                cartByTypeList.add(CartByTypeDto.builder()
                        .packageType("??????")
                        .result(false)
                        .responseMsg("????????? ?????? ????????? ????????????.")
                        .data(null)
                        .build());
            } else {
                cartByTypeList.add(CartByTypeDto.builder()
                        .packageType("??????")
                        .result(true)
                        .responseMsg("?????? ?????? ????????? ??????????????????.")
                        .data(normalData)
                        .build());
            }

            if(coldData.isEmpty()) {
                cartByTypeList.add(CartByTypeDto.builder()
                        .packageType("??????")
                        .result(false)
                        .responseMsg("????????? ?????? ????????? ????????????.")
                        .data(null)
                        .build());
            } else {
                cartByTypeList.add(CartByTypeDto.builder()
                        .packageType("??????")
                        .result(true)
                        .responseMsg("?????? ?????? ????????? ??????????????????.")
                        .data(coldData)
                        .build());
            }

            if(frozenData.isEmpty()) {
                cartByTypeList.add(CartByTypeDto.builder()
                        .packageType("??????")
                        .result(false)
                        .responseMsg("????????? ?????? ????????? ????????????.")
                        .data(null)
                        .build());
            } else {
                cartByTypeList.add(CartByTypeDto.builder()
                        .packageType("??????")
                        .result(true)
                        .responseMsg("?????? ?????? ????????? ??????????????????.")
                        .data(frozenData)
                        .build());
            }

            cartRespDto = CartRespDto.builder()
                    .result(true)
                    .responseMsg("???????????? ????????? ??????????????????. ??? ??????: "+cartDetailList.size())
                    .deliveryFee(cart.get(0).getDelivery_fee())
                    .cartByTypeList(cartByTypeList)
                    .build();

        } else {
            cartRespDto = CartRespDto.builder()
                    .result(false)
                    .responseMsg("??????????????? ???????????????.")
                    .deliveryFee(0)
                    .cartByTypeList(null)
                    .build();
        }

        return cartRespDto;
    }

    @Transactional
    public Boolean updateCart(Long userId, Long productId, CartUpdateDto cartUpdateDto){
        List<CartDetail> cartDetailList = findCartDetail(findCartByUser(userId));
        boolean result = false;

        if(!cartDetailList.isEmpty()){
            for(CartDetail cartDetail:cartDetailList){
                if(Objects.equals(cartDetail.getProduct().getId(), productId)){
                    cartDetail.updateQuantity(cartDetail, cartUpdateDto.getQuantity());
                    em.persist(cartDetail);
                    result = true;
                }
            }
        }

        return result;

//        List<Cart> cart = findCartByUser(userId);
//
//        if(!cart.isEmpty()) {
//            em.createQuery("update CartDetail c set c.quantity=:quantity " +
//                            "where c.product.id=:productId and c.cart.id=:cartId")
//                    .setParameter("quantity", cartUpdateDto.getQuantity())
//                    .setParameter("productId", productId)
//                    .setParameter("cartId", cart.get(0).getId())
//                    .executeUpdate();
//            return true;
//        } else {
//            return false;
//        }
    }

    public void mergeCart(CartDetail cartDetail, int quantity) {
        cartDetail.updateQuantity(cartDetail, quantity);
        em.persist(cartDetail);
    }
}
