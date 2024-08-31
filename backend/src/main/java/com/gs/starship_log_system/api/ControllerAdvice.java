package com.gs.starship_log_system.api;

import com.gs.starship_log_system.exception.InvalidLoginAttemptException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> invalidLoginAttemptException(InvalidLoginAttemptException ex) {
        return ResponseEntity.status(401).body("Invalid login attempt - access denied");
    }
}
