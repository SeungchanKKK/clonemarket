package com.marketkutty.marketkutty.service;

import com.marketkutty.marketkutty.model.dto.requestDto.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartPostRepository cartPostRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final MainProductRepository mainProductRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

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

    @Transactional
    public Boolean mergeCart(Long userId, List<CartMergeDto> cartMergeDtoList) {
        Cart cart;
        int totalPrice = 0;
        List<CartDetail> cartDetailList;

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다."));
        List<Cart> cartList = cartRepository.findCartByUser(userId);

        //해당 유저의 카트가 없으면 카트 생성
        if(cartList.isEmpty()){
            cart = createCart(cartMergeDtoList, totalPrice, user);
            //카트 디테일 생성
            for(CartMergeDto cartMergeDto: cartMergeDtoList) {
                createCartDetail(cartMergeDto, cart);
            }
        } else { //해당 유저의 카트가 있으면 기존 카트에 추가
            cart = cartRepository.findCartByUser(userId).get(0);
            cartDetailList = cartRepository.findCartDetail(cartList);

            //기존 카트에 동일 프로덕트 아이디가 있으면, quantity 업데이트, 없으면 카트 디테일 생성
            for(CartDetail cartDetail:cartDetailList){
                for(CartMergeDto cartMergeDto:cartMergeDtoList){
                    if(cartDetail.getProduct().getId()==cartMergeDto.getProductId()){
                        cartDetail.updateQuantity(cartDetail, cartMergeDto.getQuantity());
                    }
                }
            }

            List<CartDetail> newCartDetailList = cartDetailList.stream()
                    .filter(newCartDetail -> cartMergeDtoList.stream()
                            .noneMatch(cartMergeDto -> newCartDetail.getProduct().getId().equals(newCartDetail.getProduct().getId())))
                    .collect(Collectors.toList());
            for(CartDetail newCartDetail:newCartDetailList){
                cartRepository.saveCartDetail(newCartDetail);
            }
        }
        return true;
    }

    private void createCartDetail(CartMergeDto cartMergeDto, Cart cart) {
        Product product = productRepository.findProduct(cartMergeDto.getProductId());
        CartDetail cartDetail = CartDetail.builder()
                .id(null)
                .cart(cart)
                .product(product)
                .quantity(cartMergeDto.getQuantity())
                .build();
        cartRepository.saveCartDetail(cartDetail);
    }

    private Cart createCart(List<CartMergeDto> cartMergeDtoList, int totalPrice, User user) {
        Cart cart;
        Product product;
        //베달비 먼저 계산
        for(CartMergeDto cartMergeDto: cartMergeDtoList){
            product = productRepository.findProduct(cartMergeDto.getProductId());
            totalPrice += product.getPrice()*cartMergeDto.getQuantity();
        }

        if(totalPrice >= 50000){
            cart = Cart.builder()
                    .id(null)
                    .user(user)
                    .delivery_fee(0)
                    .build();
        } else {
            cart = Cart.builder()
                    .id(null)
                    .user(user)
                    .delivery_fee(3000)
                    .build();
        }
        //카트 생성
        cartRepository.saveCart(cart);
        return cart;
    }
}

