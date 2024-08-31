package com.gs.starship_log_system.service;

import com.gs.starship_log_system.exception.InvalidLoginAttemptException;
import com.gs.starship_log_system.model.LoginRequest;
import com.gs.starship_log_system.model.LoginResponse;
import com.gs.starship_log_system.repository.TokenRepository;

import java.util.UUID;

public class LoginSecurityService {

    private final TokenRepository tokenRepository;

    public LoginSecurityService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        if (invalidLoginRequest(loginRequest)) {
            throw new InvalidLoginAttemptException();
        }

        String token = UUID.randomUUID().toString();
        tokenRepository.saveToken(token, loginRequest.getUsername());

        return LoginResponse.builder()
                .token(token)
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
