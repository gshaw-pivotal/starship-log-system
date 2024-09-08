package com.gs.starship_log_system.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Builder
@Data
public class Log {

    @Id
    private UUID id;

    private UUID userId;

    private String logDate;

    private String posting;

    private String location;

    private String header;

    private String content;
}
