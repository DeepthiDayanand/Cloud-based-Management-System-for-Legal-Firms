package com.capstonelegal.employee.repository;

import com.capstonelegal.employee.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, String>, JpaSpecificationExecutor<Employee> {
}
