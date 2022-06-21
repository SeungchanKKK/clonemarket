package com.marketkutty.marketkutty.model.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRespDto {
    private Boolean result;
    private List<Depth1Dto> depth1DtoList;
    private String responseMsg;

    @Builder
    public CategoryRespDto(Boolean result, List<Depth1Dto> depth1DtoList, String responseMsg) {
        this.result = result;
        this.depth1DtoList = depth1DtoList;
        this.responseMsg = responseMsg;
    }
}
