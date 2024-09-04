package com.gs.starship_log_system.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class User {

    private UUID id;

    private String name;

    private String rank;

    private String username;
}
