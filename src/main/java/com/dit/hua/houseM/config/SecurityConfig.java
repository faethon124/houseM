package com.dit.hua.houseM.config;

import com.dit.hua.houseM.controllers.thymeleaf.AuthController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    private final AuthenticationSuccessHandler authSuccessHandler;

    public SecurityConfig(AuthSuccessHandler authSuccessHandler) {
        this.authSuccessHandler = authSuccessHandler;
    }

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
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/auth/", "/login", "/signup").permitAll()
                        .requestMatchers("/admin/").hasRole("ADMIN")
                        .requestMatchers("/owners/").hasRole("OWNER")
                        .requestMatchers("/renters/").hasRole("RENTER")
                        .requestMatchers("/properties/").hasAnyRole("ADMIN", "RENTER")
                        .requestMatchers("/application-forms/").hasRole("ADMIN")
                        .requestMatchers("/owners/properties/").hasRole("OWNER")
                        .requestMatchers("/application-forms/owners/").hasRole("OWNER")
                        .requestMatchers("/application-forms/renters/**").hasRole("RENTER")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(authSuccessHandler) // Use custom handler
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());

        return http.build();
    }
}












