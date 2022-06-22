package com.marketkutty.marketkutty.model.dto.responseDto;

import com.marketkutty.marketkutty.model.dto.CartDetailDto;
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
    private Long id;
    private int delivery_fee;
    private List<CartDetailDto> cartDetailList;

    private String responseMsg;

    @Builder
    public CartRespDto(Boolean result, Long id, int delivery_fee, List<CartDetailDto> cartDetailList, String responseMsg) {
        this.result = result;
        this.id = id;
        this.delivery_fee = delivery_fee;
        this.cartDetailList = cartDetailList;
        this.responseMsg = responseMsg;
    }
}
