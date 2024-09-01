package com.gs.starship_log_system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class LoginRequest {

    private String username;

    private String password;

    private String rank;

    private String name;

    public LoginRequest() {}
}
