package com.gs.starship_log_system.service;

import com.gs.starship_log_system.exception.InvalidLogEntryException;
import com.gs.starship_log_system.model.Log;
import com.gs.starship_log_system.model.User;

import java.util.Collections;
import java.util.List;

public class LogEntryService {

    public List<Log> getLogsForUser(User user) {
        return Collections.emptyList();
    }

    public boolean storeNewLog(User user, Log log) {
        validateNewLog(log);
        return false;
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
