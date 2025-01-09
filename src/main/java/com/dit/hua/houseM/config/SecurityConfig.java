package com.dit.hua.houseM.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing or APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/owners/**").permitAll() // Public access for owner endpoints
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Restrict admin endpoints
                        .anyRequest().authenticated() // Require authentication for other endpoints
                )
                .httpBasic(withDefaults()); // Correct HTTP Basic configuration

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}




