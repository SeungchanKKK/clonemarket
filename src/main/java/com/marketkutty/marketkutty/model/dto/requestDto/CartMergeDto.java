package com.marketkutty.marketkutty.model.dto.requestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartMergeDto {
    private Long productId;
    private int quantity;

    @Builder
    public CartMergeDto(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
