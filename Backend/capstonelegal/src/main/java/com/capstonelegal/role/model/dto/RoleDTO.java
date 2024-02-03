package com.capstonelegal.role.model.dto;

import com.capstonelegal.role.model.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link Role}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDTO implements Serializable {
    private String roleId;
    private String roleName;
    private String roleDescription;

    /**
     * Create a RoleDTO instance from a Role entity.
     *
     * @param role The Role entity from which to create the RoleDTO.
     * @return A new RoleDTO instance.
     */
    public static RoleDTO fromEntity(Role role) {
        return new RoleDTO()
                .setRoleId(role.getRoleId())
                .setRoleName(role.getRoleName())
                .setRoleDescription(role.getRoleDescription());
    }
}
