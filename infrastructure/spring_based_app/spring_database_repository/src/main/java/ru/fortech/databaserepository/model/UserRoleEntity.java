package ru.fortech.databaserepository.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "personrole")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "rolename")
    private String roleName;
}
