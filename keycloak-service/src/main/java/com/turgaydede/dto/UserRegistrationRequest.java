package com.turgaydede.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}