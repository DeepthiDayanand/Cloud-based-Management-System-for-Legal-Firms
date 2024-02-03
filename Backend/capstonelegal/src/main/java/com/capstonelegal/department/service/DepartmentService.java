package com.capstonelegal.department.service;

import com.capstonelegal.department.model.dto.DepartmentDTO;
import com.capstonelegal.department.model.entities.Department;
import com.capstonelegal.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing departments.
 */
@Service
@Slf4j
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Find a department by its ID.
     *
     * @param departmentId The ID of the department to find.
     * @return The DepartmentDTO representing the department with the given ID, or null if not found.
     */
    public DepartmentDTO findById(String departmentId) {
        log.info("Finding department by ID: {}", departmentId);
        Optional<Department> department = departmentRepository.findById(departmentId);
        return department.map(DepartmentDTO::fromEntity).orElse(null);
    }

    /**
     * Create a new department.
     *
     * @param departmentDTO The DepartmentDTO representing the new department to create.
     * @return The created DepartmentDTO.
     */
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        log.info("Creating department: {}", departmentDTO);
        Department department = new Department();
        department.setDepartmentId(departmentDTO.getDepartmentId());
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDepartmentDescription(departmentDTO.getDepartmentDescription());
        Department createdDepartment = departmentRepository.save(department);
        return DepartmentDTO.fromEntity(createdDepartment);
    }

    /**
     * Update an existing department.
     *
     * @param departmentId  The ID of the department to update.
     * @param departmentDTO The DepartmentDTO representing the updated department data.
     * @return The updated DepartmentDTO.
     */
    public DepartmentDTO updateDepartment(String departmentId, DepartmentDTO departmentDTO) {
        log.info("Updating department with ID {}: {}", departmentId, departmentDTO);
        Optional<Department> existingDepartment = departmentRepository.findById(departmentId);
        if (existingDepartment.isPresent()) {
            Department department = existingDepartment.get();
            department.setDepartmentName(departmentDTO.getDepartmentName());
            department.setDepartmentDescription(departmentDTO.getDepartmentDescription());
            Department updatedDepartment = departmentRepository.save(department);
            return DepartmentDTO.fromEntity(updatedDepartment);
        } else {
            throw new IllegalArgumentException("Department with ID " + departmentId + " not found.");
        }
    }

    /**
     * Delete a department by its ID.
     *
     * @param departmentId The ID of the department to delete.
     */
    public void deleteDepartment(String departmentId) {
        log.info("Deleting department with ID: {}", departmentId);
        departmentRepository.deleteById(departmentId);
    }

    /**
     * List departments with pagination and filter by department name.
     *
     * @param departmentName The name of the department to filter by (optional).
     * @param pageable       Pageable object for pagination.
     * @return Page of DepartmentDTO.
     */
    public Page<DepartmentDTO> listDepartments(String departmentName, Pageable pageable) {
        log.info("Listing departments with name filter: {}", departmentName);
        if (departmentName != null && !departmentName.isEmpty()) {
            return departmentRepository.findByDepartmentNameContainingIgnoreCase(departmentName, pageable)
                    .map(DepartmentDTO::fromEntity);
        } else {
            return departmentRepository.findAll(pageable)
                    .map(DepartmentDTO::fromEntity);
        }
    }
}
