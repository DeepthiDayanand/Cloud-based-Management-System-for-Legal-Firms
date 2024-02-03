package com.capstonelegal.department.repository;

import com.capstonelegal.department.model.entities.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface DepartmentRepository  extends JpaRepository<Department, String>, JpaSpecificationExecutor<Department> {
    // Custom method to find departments by a partial name match (case-insensitive)
    @Query("SELECT d FROM Department d WHERE LOWER(d.departmentName) LIKE LOWER(CONCAT('%', :departmentName, '%'))")
    Page<Department> findByDepartmentNameContainingIgnoreCase(String departmentName, Pageable pageable);
}
