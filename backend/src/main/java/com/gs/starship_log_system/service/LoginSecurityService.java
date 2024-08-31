package com.gs.starship_log_system.service;

import com.gs.starship_log_system.exception.InvalidLoginAttemptException;
import com.gs.starship_log_system.model.LoginRequest;
import com.gs.starship_log_system.model.LoginResponse;

import java.util.UUID;

public class LoginSecurityService {

    public LoginResponse login(LoginRequest loginRequest) {
        if (invalidLoginRequest(loginRequest)) {
            throw new InvalidLoginAttemptException();
        }

        return LoginResponse.builder()
                .token(UUID.randomUUID().toString())
                .build();
    }

    private boolean invalidLoginRequest(LoginRequest loginRequest) {
        if (loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            return true;
        }

        if (loginRequest.getUsername().isBlank() || loginRequest.getPassword().isBlank()) {
            return true;
        }

        return false;
    }
}
