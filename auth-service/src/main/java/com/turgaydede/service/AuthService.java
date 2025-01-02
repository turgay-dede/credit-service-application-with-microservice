package com.turgaydede.service;

import com.turgaydede.dto.TokenRequest;
import com.turgaydede.dto.TokenResponse;

public interface AuthService {
    TokenResponse getToken(TokenRequest request);
    TokenResponse getTokenWithRefreshToken(String refreshToken);
}
