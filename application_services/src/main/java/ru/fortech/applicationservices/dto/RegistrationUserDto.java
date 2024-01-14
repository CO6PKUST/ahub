package ru.fortech.applicationservices.dto;

import lombok.Data;

@Data
public class RegistrationUserDto {

    private String email;
    private String password;
    private String userName;

}
