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

    @Override
    public void removeToken(String username) {
        String associatedToken = userMap.get(username);

        userMap.remove(username);
        tokenMap.remove(associatedToken);
    }

    @Override
    public String getUsername(String token) {
        return tokenMap.get(token);
    }
}
