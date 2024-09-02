package com.gs.starship_log_system.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Log {

    private String id;

    private String userId;

    private String logDate;

    private String header;

    private String content;
}
