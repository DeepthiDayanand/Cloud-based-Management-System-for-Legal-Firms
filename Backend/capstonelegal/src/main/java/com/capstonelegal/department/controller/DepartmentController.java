package com.capstonelegal.department.controller;

import com.capstonelegal.department.model.dto.DepartmentDTO;
import com.capstonelegal.department.service.DepartmentService;
import com.capstonelegal.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * REST controller for managing {@link com.capstonelegal.department.model.entities.Department}.
 */
@RestController
@RequestMapping("/v1/departments")
@RequiredArgsConstructor
@Slf4j
@Api(value = "Department Controller", tags ="05 - Department Management", description = "Operations pertaining to departments in Capstone Legal")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/{departmentId}")
    @ApiOperation(value = "Find Department by ID", response = DepartmentDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved department"),
            @ApiResponse(responseCode = "404", description = "Department not found")
    })
    public ResponseEntity<DepartmentDTO> getDepartment(@ApiParam(value = "ID of the department to retrieve", required = true) @PathVariable String departmentId) {
        log.info("Request to get Department: {}", departmentId);
        DepartmentDTO departmentDTO = departmentService.findById(departmentId);
        return Optional.ofNullable(departmentDTO)
                .map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Create a new Department", response = DepartmentDTO.class)
    @ApiResponse(responseCode = "201", description = "Successfully created department")
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        log.info("Request to create Department: {}", departmentDTO);
        departmentDTO.setDepartmentId(UUIDUtil.generateUUID());
        DepartmentDTO result = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{departmentId}")
    @ApiOperation(value = "Update an existing Department by ID", response = DepartmentDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated department"),
            @ApiResponse(responseCode = "404", description = "Department not found")
    })
    public ResponseEntity<DepartmentDTO> updateDepartment(
            @ApiParam(value = "ID of the department to update", required = true) @PathVariable String departmentId,
            @Valid @RequestBody DepartmentDTO departmentDTO) {
        log.info("Request to update Department with ID {}: {}", departmentId, departmentDTO);
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(departmentId, departmentDTO);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{departmentId}")
    @ApiOperation(value = "Delete a Department by its ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted department")
    public ResponseEntity<Void> deleteDepartment(@ApiParam(value = "ID of the department to delete", required = true) @PathVariable String departmentId) {
        log.info("Request to delete Department with ID: {}", departmentId);
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @ApiOperation(value = "List all Departments with optional filtering by department name", response = Page.class)
    public ResponseEntity<Page<DepartmentDTO>> listDepartments(
            @ApiParam(value = "Optional name filter") @RequestParam(required = false) String departmentName,
            Pageable pageable) {
        log.info("Request to list Departments with filter: {}", departmentName);
        Page<DepartmentDTO> page = departmentService.listDepartments(departmentName, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
