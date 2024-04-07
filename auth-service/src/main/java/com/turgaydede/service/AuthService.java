package com.turgaydede.service;

import com.turgaydede.dto.TokenRecordRequest;
import com.turgaydede.dto.TokenResponse;

public interface AuthService {
    TokenResponse getToken(TokenRecordRequest request);
    TokenResponse getTokenWithRefreshToken(String refreshToken);
}
