package ru.fortech.applicationservices.dto;

import lombok.Data;

@Data
public class JwtRequest {

    private String userName;
    private String password;
}
