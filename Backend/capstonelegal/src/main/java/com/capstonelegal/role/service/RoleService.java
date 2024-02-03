package com.capstonelegal.role.service;

import com.capstonelegal.role.model.dto.RoleDTO;
import com.capstonelegal.role.model.entities.Role;
import com.capstonelegal.role.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import com.capstonelegal.common.exception.ResourceNotFoundException;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public Page<RoleDTO> getRoles(Pageable pageable) {
        Page<Role> rolePage = roleRepository.findAll(pageable);
        return rolePage.map(role -> modelMapper.map(role, RoleDTO.class));
    }

    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);
        role = roleRepository.save(role);
        return modelMapper.map(role, RoleDTO.class);
    }

    public RoleDTO updateRole(String roleId, RoleDTO roleDTO) {
        Role existingRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));

        // Update fields in existingRole with data from roleDTO
        // Example: existingRole.setRoleName(roleDTO.getRoleName());

        existingRole = roleRepository.save(existingRole);
        return modelMapper.map(existingRole, RoleDTO.class);
    }

    public RoleDTO viewRole(String roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));
        return modelMapper.map(role, RoleDTO.class);
    }

    public void deleteRole(String roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));
        roleRepository.delete(role);
    }
}
