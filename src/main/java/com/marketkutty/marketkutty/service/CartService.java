package com.marketkutty.marketkutty.service;

import com.marketkutty.marketkutty.model.dto.responseDto.CartRespDto;
import com.marketkutty.marketkutty.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public CartRespDto getCart(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }
}
