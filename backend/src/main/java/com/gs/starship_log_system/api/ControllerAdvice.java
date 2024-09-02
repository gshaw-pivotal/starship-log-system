package com.gs.starship_log_system.api;

import com.gs.starship_log_system.exception.InvalidAccessAttemptException;
import com.gs.starship_log_system.exception.InvalidLogEntryException;
import com.gs.starship_log_system.exception.InvalidLoginAttemptException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> invalidLoginAttemptException(InvalidLoginAttemptException ex) {
        return ResponseEntity.status(401).body("Invalid login attempt - access denied");
    }

    @ExceptionHandler
    public ResponseEntity<String> invalidAccessAttemptException(InvalidAccessAttemptException ex) {
        return ResponseEntity.status(401).body("Invalid access attempt - access denied");
    }

    @ExceptionHandler
    public ResponseEntity<String> invalidLogEntryException(InvalidLogEntryException ex) {
        return ResponseEntity.status(400).body("Invalid log entry - log not stored");
    }
}
