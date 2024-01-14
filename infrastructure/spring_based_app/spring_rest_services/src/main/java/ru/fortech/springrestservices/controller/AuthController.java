package ru.fortech.springrestservices.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.fortech.applicationservices.exceptions.BaseException;
import ru.fortech.applicationservices.services.AuthService;
import ru.fortech.applicationservices.dto.JwtRequest;
import ru.fortech.applicationservices.dto.RegistrationUserDto;

@RestController
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) throws BaseException {
        return authService.createAuthToken(jwtRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) throws BaseException{
        return authService.createNewUser(registrationUserDto);
    }



}
