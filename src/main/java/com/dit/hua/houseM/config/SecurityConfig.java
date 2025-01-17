package com.dit.hua.houseM.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (enable in production with proper configuration)
//                .authorizeHttpRequests(auth -> auth
//                                // Authentication endpoints are open to everyone
//                                .requestMatchers("/api/auth/**").permitAll()
//
//                                // Admins have full access
//                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
//                                .requestMatchers("/api/owners/**").hasRole("ADMIN")
//                                .requestMatchers("/api/renters/**").hasRole("ADMIN")
//                                .requestMatchers("/api/properties/**").hasAnyRole("ADMIN", "RENTER")
//                                .requestMatchers("/api/application-forms/**").hasRole("ADMIN")
//
//                                // Owners can access their properties and manage applications
//                                .requestMatchers("/api/owners/properties/**").hasRole("OWNER")
//                                .requestMatchers("/api/application-forms/owners/**").hasRole("OWNER")
//
//                                // Renters can view properties and create applications
////                        .requestMatchers("/api/properties/**").hasRole("RENTER")
//                                .requestMatchers("/api/application-forms/renters/**").hasRole("RENTER")
//
//                                // All other requests require authentication
//                                .anyRequest().authenticated()
//                )
//                .httpBasic(httpBasic -> {}) // Use HTTP Basic Authentication
//                .formLogin(formLogin -> formLogin.disable()); // Disable default login page
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web.ignoring().requestMatchers("/api/**");  // You can specify the URLs you want to ignore here.
        }
}








