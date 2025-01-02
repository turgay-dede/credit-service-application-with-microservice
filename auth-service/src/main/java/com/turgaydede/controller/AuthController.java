package com.turgaydede.controller;

import com.turgaydede.dto.RefreshTokenRecordRequest;
import com.turgaydede.dto.TokenRequest;
import com.turgaydede.dto.TokenResponse;
import com.turgaydede.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/token")
    public TokenResponse getToken(@RequestBody TokenRequest request) {
        return authService.getToken(request);
    }

    @PostMapping("/refresh-token")
    public TokenResponse getTokenWithRefreshToken(@RequestBody RefreshTokenRecordRequest request) {
        return authService.getTokenWithRefreshToken(request.refreshToken());
    }
}
