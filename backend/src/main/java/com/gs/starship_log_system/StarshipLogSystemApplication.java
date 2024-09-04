package com.gs.starship_log_system;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.starship_log_system.model.ImportUser;
import com.gs.starship_log_system.service.LoginSecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class StarshipLogSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarshipLogSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(LoginSecurityService service) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<ImportUser>> typeReference = new TypeReference<>() {};

            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
            try {
                List<ImportUser> users = mapper.readValue(inputStream, typeReference);
                service.loadUsers(users);
            } catch (Exception e) {
                System.out.println("Unable to load and save users: " + e.getMessage());
            }
        };
    }
}
