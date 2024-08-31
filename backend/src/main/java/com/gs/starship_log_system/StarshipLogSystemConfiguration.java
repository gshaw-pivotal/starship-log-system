package com.gs.starship_log_system;

import com.gs.starship_log_system.repository.InMemoryTokenRepository;
import com.gs.starship_log_system.repository.TokenRepository;
import com.gs.starship_log_system.service.LoginSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarshipLogSystemConfiguration {

    @Bean
    public LoginSecurityService loginSecurityService(TokenRepository tokenRepository) {
        return new LoginSecurityService(tokenRepository);
    }

    @Bean
    public TokenRepository tokenRepository() {
        return new InMemoryTokenRepository();
    }
}
