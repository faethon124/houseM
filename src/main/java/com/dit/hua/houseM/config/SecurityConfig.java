package com.dit.hua.houseM.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs, enable for web forms if needed
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/auth/**", "/login", "/signup").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/owners/**").hasRole("ADMIN")
                        .requestMatchers("/renters/**").hasRole("ADMIN")
                        .requestMatchers("/properties/**").hasAnyRole("ADMIN", "RENTER")
                        .requestMatchers("/application-forms/**").hasRole("ADMIN")
                        .requestMatchers("/owners/properties/**").hasRole("OWNER")
                        .requestMatchers("/application-forms/owners/**").hasRole("OWNER")
                        .requestMatchers("/application-forms/renters/**").hasRole("RENTER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}











