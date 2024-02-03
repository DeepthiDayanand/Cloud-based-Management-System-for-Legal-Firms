package com.capstonelegal.role.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role", schema = "capstonelegalschema", indexes = {
        @Index(name = "idx_role_name", columnList = "role_name")
})
public class Role {
    @Id
    @Column(name = "role_id", nullable = false, length = 50)
    private String roleId;

    @Column(name = "role_name", nullable = false, length = 100)
    private String roleName;

    @Column(name = "role_description", nullable = false, length = 300)
    private String roleDescription;

}