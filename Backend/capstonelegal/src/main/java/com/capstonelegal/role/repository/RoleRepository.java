package com.capstonelegal.role.repository;

import com.capstonelegal.role.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository  extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {
}
