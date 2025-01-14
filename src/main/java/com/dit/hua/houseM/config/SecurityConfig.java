package com.dit.hua.houseM.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Define the PasswordEncoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define the SecurityFilterChain bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for development purposes
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Allow public access to authentication endpoints
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Only ADMIN can access admin endpoints
                        .requestMatchers("/api/owners/**").hasAnyRole("ADMIN", "OWNER") // Owners and Admins can access owner endpoints
                        .requestMatchers("/api/renters/**").hasAnyRole("ADMIN", "RENTER") // Renters and Admins can access renter endpoints
                        .anyRequest().authenticated() // Require authentication for all other endpoints
                )
                .httpBasic(httpBasic -> {}) // Use HTTP Basic authentication
                .formLogin(formLogin -> formLogin.disable()); // Disable default form login
        return http.build();
    }

    // Define the AuthenticationManager bean
    @Bean
    public AuthenticationConfiguration authenticationConfiguration() {
        return new AuthenticationConfiguration();
    }
}

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/api/**");  // You can specify the URLs you want to ignore here.
//    }





