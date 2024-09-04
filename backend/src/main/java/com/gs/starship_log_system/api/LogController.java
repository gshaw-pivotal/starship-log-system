package com.gs.starship_log_system.api;

import com.gs.starship_log_system.exception.InvalidAccessAttemptException;
import com.gs.starship_log_system.model.Log;
import com.gs.starship_log_system.model.User;
import com.gs.starship_log_system.service.LogEntryService;
import com.gs.starship_log_system.service.LoginSecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LogController {

    private final LoginSecurityService loginSecurityService;

    private final LogEntryService logEntryService;

    public LogController(
            LoginSecurityService loginSecurityService,
            LogEntryService logEntryService
    ) {
        this.loginSecurityService = loginSecurityService;
        this.logEntryService = logEntryService;
    }

    @GetMapping("/logs")
    public ResponseEntity<List<Log>> getLogs(@RequestHeader("X-API-KEY") String token) {
        User user = validateToken(token);

        // Valid user, retrieve their logs
        List<Log> logs = logEntryService.getLogsForUser(user);

        return ResponseEntity.status(200).body(logs);
    }

    @GetMapping("/log/{logId}")
    public ResponseEntity<Log> getLog(@RequestHeader("X-API-KEY") String token, @PathVariable("logId") String logId) {
        User user = validateToken(token);

        Log log = logEntryService.getLog(user, logId);

        if (log != null) {
            return ResponseEntity.ok(log);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/log")
    public ResponseEntity addLog(@RequestHeader("X-API-KEY") String token, @RequestBody Log newLog) {
        User user = validateToken(token);

        // Valid user, store their new log
        if (logEntryService.storeNewLog(user, newLog)) {
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    private User validateToken(String token) {
        if (token == null || token.isBlank()) {
            throw new InvalidAccessAttemptException();
        }

        User user = loginSecurityService.getUser(token);

        if (user == null) {
            throw new InvalidAccessAttemptException();
        }

        return user;
    }
}
