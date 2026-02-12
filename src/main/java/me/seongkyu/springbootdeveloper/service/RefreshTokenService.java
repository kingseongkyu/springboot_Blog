package me.seongkyu.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.seongkyu.springbootdeveloper.domain.RefreshToken;
import me.seongkyu.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {  //전달받은 리프레시 토큰으로 리프레시 토큰 객체를 검색해서 전달하는 메서드 구현(find)
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));

    }
}
