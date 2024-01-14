package ru.fortech.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Data
public class User {
    private Integer userId;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private String userName;
    private boolean enabled;
    private Collection<UserRole> userRoles;
}
