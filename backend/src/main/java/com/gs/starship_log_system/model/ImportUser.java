package com.gs.starship_log_system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class ImportUser {

    private UUID id;

    private String username;

    private String password;

    private String rank;

    private String name;

    public ImportUser() {}
}
