package com.energizeglobal.internship.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private String username;
    private LocalDate birthday;
    private String email;
    private String country;
    private boolean isAdmin;

    public User(String username, LocalDate birthday, String email, String country) {
        this.username = username;
        this.birthday = birthday;
        this.email = email;
        this.country = country;
    }

    public User(String username, LocalDate birthday, String email, String country, boolean isAdmin) {
        this.username = username;
        this.birthday = birthday;
        this.email = email;
        this.country = country;
        this.isAdmin = isAdmin;
    }
}
