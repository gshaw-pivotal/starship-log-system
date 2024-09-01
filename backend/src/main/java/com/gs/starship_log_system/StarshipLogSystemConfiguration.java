package com.gs.starship_log_system;

import com.gs.starship_log_system.repository.H2UserRepository;
import com.gs.starship_log_system.repository.InMemoryTokenRepository;
import com.gs.starship_log_system.repository.TokenRepository;
import com.gs.starship_log_system.repository.UserRepository;
import com.gs.starship_log_system.service.LoginSecurityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarshipLogSystemConfiguration {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPass;

    @Bean
    public LoginSecurityService loginSecurityService(TokenRepository tokenRepository, UserRepository userRepository) {
        return new LoginSecurityService(tokenRepository, userRepository);
    }

    @Bean
    public TokenRepository tokenRepository() {
        return new InMemoryTokenRepository();
    }

    @Bean
    public UserRepository userRepository() {
        return new H2UserRepository(dbUrl, dbUser, dbPass);
    }
}
