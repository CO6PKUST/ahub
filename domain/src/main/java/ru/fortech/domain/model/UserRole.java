package ru.fortech.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRole {
    private Integer roleId;
    private String roleName;
}
