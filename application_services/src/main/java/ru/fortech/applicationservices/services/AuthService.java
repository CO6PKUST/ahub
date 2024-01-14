package ru.fortech.applicationservices.services;


import org.springframework.http.ResponseEntity;
import ru.fortech.applicationservices.dto.JwtRequest;
import ru.fortech.applicationservices.dto.RegistrationUserDto;

public interface AuthService {
    ResponseEntity<?> createAuthToken(JwtRequest jwtRequest);


    ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto);

}
