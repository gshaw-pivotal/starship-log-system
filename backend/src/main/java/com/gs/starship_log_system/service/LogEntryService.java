package com.gs.starship_log_system.service;

import com.gs.starship_log_system.exception.InvalidLogEntryException;
import com.gs.starship_log_system.model.Log;
import com.gs.starship_log_system.model.User;
import com.gs.starship_log_system.repository.StarshipLogRepository;

import java.util.List;
import java.util.UUID;

public class LogEntryService {

    private final StarshipLogRepository starshipLogRepository;

    public LogEntryService(StarshipLogRepository starshipLogRepository) {
        this.starshipLogRepository = starshipLogRepository;
    }

    public List<Log> getLogsForUser(User user) {
        return starshipLogRepository.findByUserId(user.getId());
    }

    public Log getLog(User user, String logId) {
        return starshipLogRepository.findByIdAndUserId(UUID.fromString(logId), user.getId());
    }

    public boolean storeNewLog(User user, Log log) {
        validateNewLog(log);

        log.setId(UUID.randomUUID());
        log.setUserId(user.getId());

        starshipLogRepository.save(log);

        return true;
    }

    private void validateNewLog(Log log) {
        if (log == null) {
            throw new InvalidLogEntryException();
        }

        if (
                log.getLogDate() == null || log.getPosting() == null ||
                log.getLocation() == null ||log.getHeader() == null ||
                log.getContent() == null
        ) {
            throw new InvalidLogEntryException();
        }

        if (
                log.getLogDate().isBlank() || log.getPosting().isBlank() ||
                log.getLocation().isBlank() || log.getHeader().isBlank() ||
                log.getContent().isBlank()
        ) {
            throw new InvalidLogEntryException();
        }
    }
}
