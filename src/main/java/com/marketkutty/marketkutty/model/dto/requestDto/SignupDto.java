package com.marketkutty.marketkutty.model.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SignupDto {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private int phone;
    private String address;
    private String addressDetail;
    private String zonecode;

}

