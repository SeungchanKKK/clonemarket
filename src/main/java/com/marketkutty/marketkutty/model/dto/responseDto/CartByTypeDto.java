package com.marketkutty.marketkutty.model.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartByTypeDto {
    private String packageType;
    private Boolean result;
    private String responseMsg;
    private List<CartDataDto> data;

    @Builder
    public CartByTypeDto(String packageType, Boolean result, String responseMsg, List<CartDataDto> data) {
        this.packageType = packageType;
        this.result = result;
        this.responseMsg = responseMsg;
        this.data = data;
    }
}
