package com.energizeglobal.internship.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private LocalDate birthday;
    private String email;
    private String country;
}
