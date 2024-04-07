package com.turgaydede.controller;

import com.turgaydede.dto.UserRegistrationRequest;
import com.turgaydede.service.impl.UserServiceImpl;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public UserRegistrationRequest createUser(@RequestBody UserRegistrationRequest userRegistrationRecord) {

        return userService.createUser(userRegistrationRecord);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public UserRepresentation getUser(Principal principal) {
        return userService.getUserById(principal.getName());
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('admin_auth_client') or hasRole('user_auth_client')")
    public List<UserRepresentation> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable String userId) {
        userService.deleteUserById(userId);
    }

    @PutMapping("/{userId}/send-verify-email")
    public void sendVerificationEmail(@PathVariable String userId) {
        userService.emailVerification(userId);
    }

    @PutMapping("/update-password")
    public void updatePassword(Principal principal) {
        userService.updatePassword(principal.getName());
    }

}