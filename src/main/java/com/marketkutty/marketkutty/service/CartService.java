package com.marketkutty.marketkutty.service;

import com.marketkutty.marketkutty.model.dto.requestDto.CartDeleteDto;
import com.marketkutty.marketkutty.model.dto.requestDto.CartPostDto;
import com.marketkutty.marketkutty.model.dto.requestDto.ProductIdDto;
import com.marketkutty.marketkutty.model.dto.requestDto.CartUpdateDto;
import com.marketkutty.marketkutty.model.dto.responseDto.CartRespDto;
import com.marketkutty.marketkutty.model.dto.responseDto.UserRegisterRespDto;
import com.marketkutty.marketkutty.model.entity.Cart;
import com.marketkutty.marketkutty.model.entity.CartDetail;
import com.marketkutty.marketkutty.model.entity.Product;
import com.marketkutty.marketkutty.model.entity.User;
import com.marketkutty.marketkutty.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartPostRepository cartPostRepository;
    private final CartRepository cartRepository;

    private final CartDetailRepository cartDetailRepository;

    private final MainProductRepository mainProductRepository;

    private final UserRepository userRepository;

    public CartRespDto getCart(Long userId) {
        return cartRepository.getCart(userId);
    }

    public Boolean updateCart(Long userId, Long productId, CartUpdateDto cartUpdateDto) {
        return cartRepository.updateCart(userId, productId, cartUpdateDto);
    }

    @Transactional
    public boolean addCart(Long productId, CartPostDto cartPostDto, Long userId) {
        Optional<Cart> newcart = cartPostRepository.findCartByUser_Id(userId);
        int count = 0;
        int quantity = cartPostDto.getQuantity();
        Product product = mainProductRepository.findById(productId).get();
        User user = userRepository.findById(userId).get();
        //해당 유저의 장바구니가 있을때
        if(newcart.isPresent()){
            for(CartDetail cartDetail : newcart.get().getCartDetailList()){
                //장바구니에 같은 물건이 존재할때 숫자만 올려줌
                if (cartDetail.getProduct().getId() == productId){
                    cartDetail.plusnum(cartDetail.getQuantity()+cartPostDto.getQuantity());
                    count++;
                }
            }
            //장바구니에 일치하는 물건이 없을때
            if(count==0) {
                CartDetail cartDetail = new CartDetail(newcart.get(),product,quantity);
                cartDetailRepository.save(cartDetail);
            }
        }
        //해당유저 장바구니가 없을때
        else {
            Cart cart= new Cart(user);
           Cart cart1= cartPostRepository.save(cart);
           CartDetail cartDetail =cartDetailRepository.save(new CartDetail(cart1,product,quantity));
           cart1.addCartDeatilList(cartDetail);


        }
        return true;
    }

    @Transactional
    public boolean deleteCart(List<CartDeleteDto> cartDeleteDto, Long userId) {
        Cart cart = cartPostRepository.findCartByUser_Id(userId).orElseThrow(  () -> new NullPointerException("해당 유저의 카트가 존재하지 않습니다."));
        for(CartDetail cartDetail : cart.getCartDetailList()){
            for(CartDeleteDto productIdDto : cartDeleteDto){
                if(productIdDto.getProductId() == cartDetail.getProduct().getId()){
                    cartDetailRepository.deleteByProduct_id(productIdDto.getProductId());
                }
            }
        }
        return true;
    }
}

