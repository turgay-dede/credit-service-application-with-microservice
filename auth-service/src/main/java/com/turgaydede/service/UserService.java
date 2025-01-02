package com.turgaydede.service;

import com.turgaydede.dto.UserRegistrationRequest;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface UserService {

    UserRegistrationRequest createUser(UserRegistrationRequest userRegistrationRecord);

    UserRepresentation getUserById(String userId);

    List<UserRepresentation> getAll();

    void deleteUserById(String userId);

    void emailVerification(String userId);

    UserResource getUserResource(String userId);

    void updatePassword(String userId);
}