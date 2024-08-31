package com.gs.starship_log_system.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequest {

    private String username;

    private String password;
}
