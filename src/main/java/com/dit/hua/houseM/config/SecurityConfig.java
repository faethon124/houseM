//package com.dit.hua.houseM.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/home", "/login", "/signup").permitAll() // Public access
//                        .requestMatchers("/owner/**").hasRole("OWNER") // Only users with ROLE_OWNER can access
//                        .requestMatchers("/renter/**").hasRole("RENTER") // Only users with ROLE_RENTER can access
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login") // Custom login page
//                        .loginProcessingUrl("/login") // URL to process login form
//                        .defaultSuccessUrl("/home") // Default page after successful login
//                        .failureUrl("/login?error=true") // Redirect to login page on failure
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
//                        .permitAll()
//                );
//
//        return http.build();
//    }
//}
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
                        // Public pages
                        .requestMatchers("/", "/auth/**", "/login", "/signup").permitAll()
                        // Restricted access for admins
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Owners and renters access control
                        .requestMatchers("/owners/**").hasRole("OWNER")
                        .requestMatchers("/renters/**").hasRole("RENTER")
                        // Properties available for Admin and Renters
                        .requestMatchers("/properties/**").hasAnyRole("ADMIN", "RENTER")
                        // Application forms restricted to Admin
                        .requestMatchers("/application-forms/**").hasRole("ADMIN")
                        // Owners can access their property management
                        .requestMatchers("/owners/properties/**").hasRole("OWNER")
                        // Application forms for owners and renters
                        .requestMatchers("/application-forms/owners/**").hasRole("OWNER")
                        .requestMatchers("/application-forms/renters/**").hasRole("RENTER")
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true) // Redirect to the home page after successful login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // Redirect to login on logout
                        .permitAll()
                );

        return http.build();
    }
}












