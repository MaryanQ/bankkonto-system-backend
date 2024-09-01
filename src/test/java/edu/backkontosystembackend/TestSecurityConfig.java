package edu.backkontosystembackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class TestSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Deaktiverer CSRF-beskyttelse
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());  // Tillader alle anmodninger uden autentificering
        return http.build();
    }
}
