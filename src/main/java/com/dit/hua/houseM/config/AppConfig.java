package com.dit.hua.houseM.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class AppConfig {

    // Bean for password encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean for handling multipart file uploads (for property photos)
    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    // Application-level configuration: Photo storage directory
    @Bean
    public Path photoStoragePath() {
        Path photoPath = Paths.get(System.getProperty("user.dir"), "uploads", "photos");
        try {
            if (!Files.exists(photoPath)) {
                Files.createDirectories(photoPath);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create photo storage directory", e);
        }
        return photoPath;
    }
}
