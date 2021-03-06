package com.marketkutty.marketkutty.model.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartRespDto {

    private Boolean result;
    private String responseMsg;
    private int deliveryFee;
    private List<CartByTypeDto> cartByTypeList;

    @Builder
    public CartRespDto(Boolean result, String responseMsg, int deliveryFee, List<CartByTypeDto> cartByTypeList) {
        this.result = result;
        this.responseMsg = responseMsg;
        this.deliveryFee = deliveryFee;
        this.cartByTypeList = cartByTypeList;
    }
}
