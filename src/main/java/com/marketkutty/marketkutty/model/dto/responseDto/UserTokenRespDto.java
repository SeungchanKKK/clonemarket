package com.marketkutty.marketkutty.model.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserTokenRespDto {
    boolean result;
    String errorMsg;

    String payload;

    public UserTokenRespDto(boolean result, String errorMsg) {
        this.result = result;
        this.errorMsg = errorMsg;
    }


    public UserTokenRespDto(boolean result, String payload, String errorMsg) {
        this.result = result;
        this.payload = payload;
        this.errorMsg = errorMsg;
    }
}

