package com.turgaydede.dto;

public record TokenResponse(
    String access_token,
    int expires_in,
    int refresh_expires_in,
    String refresh_token,
    String token_type,
    int not_before_policy,
    String session_state,
    String scope
) {}
