package com.capstonelegal.employee.model.dto;

import com.capstonelegal.common.model.dto.CityDTO;
import com.capstonelegal.common.model.dto.CountryDTO;
import com.capstonelegal.common.model.dto.DistrictDTO;
import com.capstonelegal.common.model.dto.StateDTO;
import com.capstonelegal.employee.model.entities.Employee;
import com.capstonelegal.credentials.model.dto.CredentialsDTO;
import com.capstonelegal.department.model.dto.DepartmentDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Optional;

/**
 * DTO for {@link Employee}
 */
// @Data is a Lombok-provided annotation which tells Lombok to generate
// a getter, setter, equals, hashCode, toString methods, and a constructor for all final fields.
@Data

// @AllArgsConstructor is an annotation provided by Lombok
// which generates a constructor with 1 parameter for each field in your class.
@AllArgsConstructor

// @NoArgsConstructor is a Lombok-provided annotation which generates a no-args constructor.
@NoArgsConstructor

// @Accessors is a Lombok-provided annotation which can be configured
// to make setters return 'this' (the setter's class). 'chain' is set to true
// to allow for "method chaining" or "fluent-style" programming.
@Accessors(chain = true)

// @JsonIgnoreProperties is a Jackson annotation which indicates that other fields of the JSON
// data should be ignored and not included in the DTO during deserialization if they're not found in the DTO.
// 'ignoreUnknown' is set to true to ignore any unrecognized properties during deserialization.
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO implements Serializable {
    private String employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private DepartmentDTO employeeDepartment;
    private String employeeDateOfJoin;
    private String employeeStatus;
    private String employeeEmail;
    private String employeePhone;
    private CountryDTO employeeCountry;
    private StateDTO employeeState;
    private DistrictDTO employeeDistrict;
    private CityDTO employeeCity;
    private String employeeStreet1;
    private String employeeStreet2;
    private String employeeStreetZipcode;
    private String employeeDescription;
    private CredentialsDTO employeeCredentials;
    public static EmployeeDTO fromEntity(Optional<Employee> employee) {
        // Create a new EmployeeDTO instance
        EmployeeDTO employeeDTO = new EmployeeDTO();

        // Check if the Optional<Employee> is present
        if (employee.isPresent()) {
            // Get the Employee entity from the Optional
            Employee employeeEntity = employee.get();

            // Map the properties from the entity to the DTO
            employeeDTO.setEmployeeId(employeeEntity.getEmployeeId());
            employeeDTO.setEmployeeFirstName(employeeEntity.getEmployeeFirstName());
            employeeDTO.setEmployeeLastName(employeeEntity.getEmployeeLastName());
            employeeDTO.setEmployeeDepartment(DepartmentDTO.fromEntity(employeeEntity.getEmployeeDepartment()));
            employeeDTO.setEmployeeDateOfJoin(employeeEntity.getEmployeeDateOfJoin());
            employeeDTO.setEmployeeStatus(employeeEntity.getEmployeeStatus());
            employeeDTO.setEmployeeEmail(employeeEntity.getEmployeeEmail());
            employeeDTO.setEmployeePhone(employeeEntity.getEmployeePhone());
            employeeDTO.setEmployeeCountry(CountryDTO.fromCountryEntity(employeeEntity.getEmployeeCountry()));
            employeeDTO.setEmployeeState(StateDTO.fromStateEntity(employeeEntity.getEmployeeState()));
            employeeDTO.setEmployeeDistrict(DistrictDTO.fromDistrictEntity(employeeEntity.getEmployeeDistrict()));
            employeeDTO.setEmployeeCity(CityDTO.fromCityEntity(employeeEntity.getEmployeeCity()));
            employeeDTO.setEmployeeStreet1(employeeEntity.getEmployeeStreet1());
            employeeDTO.setEmployeeStreet2(employeeEntity.getEmployeeStreet2());
            employeeDTO.setEmployeeStreetZipcode(employeeEntity.getEmployeeStreetZipcode());
            employeeDTO.setEmployeeDescription(employeeEntity.getEmployeeDescription());
            employeeDTO.setEmployeeCredentials(CredentialsDTO.fromEntity(employeeEntity.getEmployeeCredentials()));
        }

        return employeeDTO;
    }
}