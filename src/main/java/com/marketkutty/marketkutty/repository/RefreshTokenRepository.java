package com.marketkutty.marketkutty.repository;


import com.marketkutty.marketkutty.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    // 액세스토큰과 리프레시토큰 값을 함께 찾아줘야 함
    Optional<RefreshToken> findByAccessTokenAndRefreshToken(String accessToken, String refreshToken);
}
