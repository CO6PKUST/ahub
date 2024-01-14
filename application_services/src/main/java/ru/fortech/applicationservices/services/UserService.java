package ru.fortech.applicationservices.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.fortech.applicationservices.dto.RegistrationUserDto;
import ru.fortech.domain.model.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByUserName(String userName);

    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
    User createNewUser(RegistrationUserDto registrationUserDto);
}
