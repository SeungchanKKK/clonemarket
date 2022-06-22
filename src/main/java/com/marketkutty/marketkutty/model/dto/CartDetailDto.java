package com.marketkutty.marketkutty.model.dto;

import com.marketkutty.marketkutty.model.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartDetailDto {

    private Long id;
    private Product product;
    private int quantity;

    @Builder
    public CartDetailDto(Long id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }
}
