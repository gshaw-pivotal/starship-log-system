package com.gs.starship_log_system.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

    private int id;

    private String name;

    private String rank;

    private String username;
}
