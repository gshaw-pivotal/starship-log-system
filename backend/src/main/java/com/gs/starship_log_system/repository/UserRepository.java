package com.gs.starship_log_system.repository;

import com.gs.starship_log_system.model.LoginRequest;

public interface UserRepository {

    boolean validCred(LoginRequest loginRequest);

    void save(LoginRequest loginRequest);
}
