package com.gs.starship_log_system.api;

import com.gs.starship_log_system.exception.InvalidAccessAttemptException;
import com.gs.starship_log_system.model.Log;
import com.gs.starship_log_system.model.User;
import com.gs.starship_log_system.service.LoginSecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogController {

    private final LoginSecurityService loginSecurityService;

    public LogController(LoginSecurityService loginSecurityService) {
        this.loginSecurityService = loginSecurityService;
    }

    @GetMapping("/logs")
    public ResponseEntity<List<Log>> getLogs(@RequestHeader("X-API-KEY") String token) {
        if (token == null || token.isBlank()) {
            throw new InvalidAccessAttemptException();
        }

        User user = loginSecurityService.getUser(token);

        if (user == null) {
            throw new InvalidAccessAttemptException();
        }

        // Valid user, retrieve their logs

        return ResponseEntity.status(200).build();
    }
}
