package com.turgaydede.controller;

import com.turgaydede.dto.UserRegistrationRequest;
import com.turgaydede.service.KeycloakUserServiceImpl;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class KeycloakController {
    private final KeycloakUserServiceImpl userEntityService;

    public KeycloakController(KeycloakUserServiceImpl userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public UserRegistrationRequest createUser(@RequestBody UserRegistrationRequest userRegistrationRecord) {

        return userEntityService.createUser(userRegistrationRecord);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public UserRepresentation getUser(Principal principal) {
        return userEntityService.getUserById(principal.getName());
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public List<UserRepresentation> getAll() {
        return userEntityService.getAll();
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable String userId) {
        userEntityService.deleteUserById(userId);
    }


    @PutMapping("/{userId}/send-verify-email")
    public void sendVerificationEmail(@PathVariable String userId) {
        userEntityService.emailVerification(userId);
    }
    @PutMapping("/update-password")
    public void updatePassword(Principal principal) {
        userEntityService.updatePassword(principal.getName());
    }

}
