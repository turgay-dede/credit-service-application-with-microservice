package com.turgaydede.service.impl;

import com.turgaydede.util.RestClient;
import com.turgaydede.dto.RestTemplateRequest;
import com.turgaydede.dto.TokenRecordRequest;
import com.turgaydede.dto.TokenResponse;
import com.turgaydede.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RestClient restClient;

    @Value("${keycloak.client}")
    private String clientId;

    @Value("${keycloak.tokenUri}")
    private String tokenUri;

    @Value("${keycloak.adminClientSecret}")
    private String clientSecret;

    @Override
    public TokenResponse getToken(TokenRecordRequest request) {
        MultiValueMap<Object, Object> formData = new LinkedMultiValueMap<>();
        formData.add("username", request.username());
        formData.add("password", request.password());
        formData.add("grant_type", "password");
        formData.add("client_id", clientId);

        restClient.setRestTemplateRequest(new RestTemplateRequest(tokenUri, request.username(), request.password()));
        return restClient.exchange(formData, TokenResponse.class);
    }

    @Override
    public TokenResponse getTokenWithRefreshToken(String refreshToken) {
        MultiValueMap<Object, Object> formData = new LinkedMultiValueMap<>();
        formData.add("client_secret", clientSecret);
        formData.add("grant_type", "refresh_token");
        formData.add("client_id", clientId);
        formData.add("refresh_token", refreshToken);

        return restClient.exchange(formData, TokenResponse.class);
    }

}
