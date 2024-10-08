package com.gs.starship_log_system.repository;

public interface TokenRepository {

    void saveToken(String token, String username);

    void removeToken(String username);

    String getUsername(String token);
}
