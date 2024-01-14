package ru.fortech.applicationlogic.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.fortech.applicationlogic.services.utils.JwtTokenUtilsImpl;
import ru.fortech.applicationservices.dto.JwtRequest;
import ru.fortech.applicationservices.dto.JwtResponse;
import ru.fortech.applicationservices.dto.RegistrationUserDto;
import ru.fortech.applicationservices.dto.UserDto;
import ru.fortech.applicationservices.exceptions.AppError;
import ru.fortech.applicationservices.services.AuthService;
import ru.fortech.domain.model.User;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserServiceImpl userService;
    private final JwtTokenUtilsImpl jwtTokenUtilsImpl;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> createAuthToken(JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName()
                    ,authRequest.getPassword()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUserName());
        String token = jwtTokenUtilsImpl.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));


    }

    @Override
    public ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto){
        if(userService.findByUserName(registrationUserDto.getUserName()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пользователь уже зарегистрирован"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(user.getUserId(), user.getEmail(), user.getUserName()));
    }


}
