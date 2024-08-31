package com.gs.starship_log_system.repository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTokenRepository implements TokenRepository {

    private Map<String, String> tokenMap = new HashMap<>();

    private Map<String, String> userMap = new HashMap<>();

    @Override
    public void saveToken(String token, String username) {
        tokenMap.put(token, username);
        userMap.put(username, token);
    }
}
