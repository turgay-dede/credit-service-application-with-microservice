package com.turgaydede.controller;

import com.turgaydede.dto.UserRegistrationRequest;
import com.turgaydede.service.AuthServiceImpl;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AuthController {
    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public UserRegistrationRequest createUser(@RequestBody UserRegistrationRequest userRegistrationRecord) {

        return authService.createUser(userRegistrationRecord);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public UserRepresentation getUser(Principal principal) {
        return authService.getUserById(principal.getName());
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public List<UserRepresentation> getAll() {
        return authService.getAll();
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable String userId) {
        authService.deleteUserById(userId);
    }


    @PutMapping("/{userId}/send-verify-email")
    public void sendVerificationEmail(@PathVariable String userId) {
        authService.emailVerification(userId);
    }
    @PutMapping("/update-password")
    public void updatePassword(Principal principal) {
        authService.updatePassword(principal.getName());
    }

}
