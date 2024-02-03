package com.capstonelegal.role.controller;

import com.capstonelegal.role.model.dto.RoleDTO;
import com.capstonelegal.role.service.RoleService;
import com.capstonelegal.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controller for managing roles.
 */
@RestController
@RequestMapping("/v1/roles")
@Api(value = "Roles Controller", tags = "10 - Role Management",description = "Operations pertaining to roles in Capstone Legal")
@Slf4j
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Get a paginated list of roles.
     *
     * @param pageable Pagination information.
     * @return A ResponseEntity containing a page of RoleDTO objects.
     */
    @GetMapping
    @ApiOperation("Get a paginated list of roles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the roles"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Page<RoleDTO>> getRoles(Pageable pageable) {
        log.info("Fetching paginated roles");
        Page<RoleDTO> roles = roleService.getRoles(pageable);
        return ResponseEntity.ok(roles);
    }

    /**
     * Create a new role.
     *
     * @param roleDTO The RoleDTO object containing role details.
     * @return A ResponseEntity containing the created RoleDTO.
     */
    @PostMapping
    @ApiOperation("Create a new role")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        log.info("Creating a new role");
        roleDTO.setRoleId(UUIDUtil.generateUUID());
        RoleDTO createdRole = roleService.createRole(roleDTO);
        return ResponseEntity.ok(createdRole);
    }

    /**
     * Update an existing role.
     *
     * @param roleId   The ID of the role to be updated.
     * @param roleDTO  The RoleDTO object containing updated role details.
     * @return A ResponseEntity containing the updated RoleDTO.
     */
    @PutMapping("/{roleId}")
    @ApiOperation("Update an existing role")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Role not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoleDTO> updateRole(
            @PathVariable String roleId,
            @RequestBody RoleDTO roleDTO) {
        log.info("Updating role with ID: {}", roleId);
        RoleDTO updatedRole = roleService.updateRole(roleId, roleDTO);
        return ResponseEntity.ok(updatedRole);
    }

    /**
     * Get details of a role.
     *
     * @param roleId The ID of the role.
     * @return A ResponseEntity containing the RoleDTO of the requested role.
     */
    @GetMapping("/{roleId}")
    @ApiOperation("Get details of a role")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the role"),
            @ApiResponse(responseCode = "404", description = "Role not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<RoleDTO> viewRole(
            @PathVariable @ApiParam("ID of the role") String roleId) {
        log.info("Fetching details of role with ID: {}", roleId);
        RoleDTO role = roleService.viewRole(roleId);
        return ResponseEntity.ok(role);
    }

    /**
     * Delete a role.
     *
     * @param roleId The ID of the role to be deleted.
     * @return A ResponseEntity with no content.
     */
    @DeleteMapping("/{roleId}")
    @ApiOperation("Delete a role")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteRole(
            @PathVariable @ApiParam("ID of the role") String roleId) {
        log.info("Deleting role with ID: {}", roleId);
        roleService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }
}
