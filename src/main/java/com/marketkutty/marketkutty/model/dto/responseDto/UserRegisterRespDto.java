package com.marketkutty.marketkutty.model.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRegisterRespDto {
    boolean result;

    public UserRegisterRespDto(boolean result) {
        this.result = result;

    }
}