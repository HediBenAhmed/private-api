package com.hedi.api;

import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private boolean isActive;
    private boolean isAuthenticated;

    private LocalDateTime lastLoginDate;
}