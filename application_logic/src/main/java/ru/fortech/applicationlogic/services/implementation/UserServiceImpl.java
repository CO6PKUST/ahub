package ru.fortech.applicationlogic.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.fortech.applicationlogic.services.utils.PasswordEncoderConfiguration;
import ru.fortech.applicationservices.dto.RegistrationUserDto;
import ru.fortech.applicationservices.services.UserRoleService;
import ru.fortech.applicationservices.services.UserService;
import ru.fortech.domain.model.User;
import ru.fortech.repositoryinterface.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserRoleServiceImpl userRoleService;
    private final PasswordEncoderConfiguration passwordEncoderConfiguration;

    @Override
    public Optional<User> findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = findByUserName(userName).orElseThrow(()-> new UsernameNotFoundException(
                String.format("пользователь '%s' не найден", userName)

        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getUserRoles().stream()
                        .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName()))
                        .collect(Collectors.toList())
        );
    }
    @Override
    public User createNewUser(RegistrationUserDto registrationUserDto){
        User user = new User();
        user.setUserName(registrationUserDto.getUserName());
        user.setEmail(registrationUserDto.getEmail());
        user.setPassword(passwordEncoderConfiguration.passwordEncoder().encode(registrationUserDto.getPassword()));
        user.setUserRoles(List.of(userRoleService.getUserRole()));
        user.setEnabled(true);
        return userRepository.save(user);
    }
}
