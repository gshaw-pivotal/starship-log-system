package com.gs.starship_log_system.api;

import com.gs.starship_log_system.model.LoginRequest;
import com.gs.starship_log_system.model.LoginResponse;
import com.gs.starship_log_system.service.LoginSecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginSecurityService loginSecurityService;

    public LoginController(LoginSecurityService loginSecurityService) {
        this.loginSecurityService = loginSecurityService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = loginSecurityService.login(request);

        return ResponseEntity.status(200).body(response);
    }
}
