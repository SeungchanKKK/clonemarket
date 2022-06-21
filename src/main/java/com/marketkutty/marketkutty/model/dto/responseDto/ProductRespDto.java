package com.marketkutty.marketkutty.model.dto.responseDto;

import com.marketkutty.marketkutty.model.dto.ProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductRespDto {
    private Boolean result;
    private List<ProductDto> productDto;
    private String responseMsg;

    @Builder
    public ProductRespDto(Boolean result, List<ProductDto> productDto, String responseMsg) {
        this.result = result;
        this.productDto = productDto;
        this.responseMsg = responseMsg;
    }

    @Builder
    public ProductRespDto(Boolean result, String responseMsg) {
        this.result = result;
        this.responseMsg = responseMsg;
    }
}
