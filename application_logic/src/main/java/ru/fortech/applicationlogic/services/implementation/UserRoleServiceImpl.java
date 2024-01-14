package ru.fortech.applicationlogic.services.implementation;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fortech.applicationservices.services.UserRoleService;
import ru.fortech.domain.model.UserRole;
import ru.fortech.repositoryinterface.repository.UserRoleRepository;
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    @Override
    public UserRole getUserRole(){
        return userRoleRepository.findByRoleName("ROLE_USER").orElseThrow();
    }
}
