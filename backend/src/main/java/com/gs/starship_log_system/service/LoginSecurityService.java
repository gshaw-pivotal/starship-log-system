package com.gs.starship_log_system.service;

import com.gs.starship_log_system.exception.InvalidLoginAttemptException;
import com.gs.starship_log_system.model.ImportUser;
import com.gs.starship_log_system.model.LoginRequest;
import com.gs.starship_log_system.model.LoginResponse;
import com.gs.starship_log_system.model.User;
import com.gs.starship_log_system.repository.TokenRepository;
import com.gs.starship_log_system.repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class LoginSecurityService {

    private final TokenRepository tokenRepository;

    private final UserRepository userRepository;

    public LoginSecurityService(TokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        if (invalidLoginRequest(loginRequest)) {
            throw new InvalidLoginAttemptException();
        }

        if (userRepository.validCred(loginRequest)) {

            String token = UUID.randomUUID().toString();
            tokenRepository.saveToken(token, loginRequest.getUsername());

            return LoginResponse.builder()
                    .token(token)
                    .build();
        }

        throw new InvalidLoginAttemptException();
    }

    public void logout(LoginRequest loginRequest) {
        tokenRepository.removeToken(loginRequest.getUsername());
    }

    public User getUser(String token) {
        String username = tokenRepository.getUsername(token);

        if (username == null) {
            return null;
        }

        return userRepository.getUser(username);
    }

    public void loadUsers(List<ImportUser> users) {
        for (ImportUser user : users) {
            userRepository.save(user);
        }
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
