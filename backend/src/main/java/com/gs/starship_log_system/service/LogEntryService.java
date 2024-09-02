package com.gs.starship_log_system.service;

import com.gs.starship_log_system.model.Log;
import com.gs.starship_log_system.model.User;

import java.util.Collections;
import java.util.List;

public class LogEntryService {

    public List<Log> getLogsForUser(User user) {
        return Collections.emptyList();
    }

    public boolean storeNewLog(User user, Log log) {
        return false;
    }
}
