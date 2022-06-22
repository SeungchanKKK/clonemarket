package com.marketkutty.marketkutty.controller;

import com.marketkutty.marketkutty.model.TokenDecode;
import com.marketkutty.marketkutty.model.dto.requestDto.CartUpdateDto;
import com.marketkutty.marketkutty.model.dto.responseDto.CartRespDto;
import com.marketkutty.marketkutty.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/api/cart")
    public CartRespDto getCart(HttpServletRequest httpRequest){
        TokenDecode decode = (TokenDecode) httpRequest.getAttribute("decode");
        Long userId = decode.getId();
        return cartService.getCart(userId);
    }

    @PutMapping("/api/cart/update/{productId}")
    public Boolean updateCart(HttpServletRequest httpRequest,
                              @PathVariable Long productId, @RequestBody CartUpdateDto cartUpdateDto){
        TokenDecode decode = (TokenDecode) httpRequest.getAttribute("decode");
        Long userId = decode.getId();
        return cartService.updateCart(userId, productId, cartUpdateDto);
    }
}
