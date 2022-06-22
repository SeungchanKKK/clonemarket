package com.marketkutty.marketkutty.model.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartDataDto {
    private Long productId;
    private String name;
    private String thumb;
    private int price;
    private int quantity;

    @Builder
    public CartDataDto(Long productId, String name, String thumb, int price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.thumb = thumb;
        this.price = price;
        this.quantity = quantity;
    }
}
