package com.gs.starship_log_system.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {

    private String token;
}
