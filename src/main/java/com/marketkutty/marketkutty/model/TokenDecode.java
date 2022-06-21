package com.marketkutty.marketkutty.model;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDecode {
    String token;
    String email;
    String nickname;
    Long id;

    public TokenDecode(DecodedJWT jwt) {
        this.token = jwt.getToken();
        this.id = Long.parseLong(jwt.getClaim("id").toString());
        this.email = jwt.getClaim("email").toString();
        this.nickname = jwt.getClaim("nickname").toString();

    }
}
