package com.marketkutty.marketkutty.model.dto.requestDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartUpdateDto {
    private int quantity;

    @Builder
    public CartUpdateDto(int quantity) {
        this.quantity = quantity;
    }
}
