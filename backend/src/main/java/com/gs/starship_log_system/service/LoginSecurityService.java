package com.gs.starship_log_system.service;

import com.gs.starship_log_system.model.LoginRequest;
import com.gs.starship_log_system.model.LoginResponse;

import java.util.UUID;

public class LoginSecurityService {

    public LoginResponse login(LoginRequest loginRequest) {
        return LoginResponse.builder()
                .token(UUID.randomUUID().toString())
                .build();
    }
}
