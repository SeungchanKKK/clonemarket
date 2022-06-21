package com.marketkutty.marketkutty.model.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginRespDto {
    boolean result;
    String errorMsg;
    String token;
    String refreshToken;
    String nickname;

    public UserLoginRespDto(boolean result, String errorMsg) {
        this.result = result;
        this.errorMsg = errorMsg;
    }

    public UserLoginRespDto(boolean result, String token, String refreshToken, String errorMsg, String nickname) {
        this.result = result;
        this.errorMsg = errorMsg;
        this.token = token;
        this.refreshToken = refreshToken;
        this.nickname= nickname;
    }
}
