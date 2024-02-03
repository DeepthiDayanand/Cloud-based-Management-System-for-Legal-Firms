package com.capstonelegal.employee.model.entities;

import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.model.entities.District;
import com.capstonelegal.common.model.entities.State;
import com.capstonelegal.credentials.model.entities.Credentials;
import com.capstonelegal.department.model.entities.Department;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "employee", schema = "capstonelegalschema")
public class Employee {
    @Id
    @Column(name = "employee_id", nullable = false, length = 50)
    private String employeeId;

    @Column(name = "employee_first_name", nullable = false, length = 100)
    private String employeeFirstName;

    @Column(name = "employee_last_name", nullable = false, length = 100)
    private String employeeLastName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_department_id", nullable = false)
    private Department employeeDepartment;

    @Column(name = "employee_date_of_join", nullable = false, length = 50)
    private String employeeDateOfJoin;

    @Column(name = "employee_status", nullable = false, length = 100)
    private String employeeStatus;

    @Column(name = "employee_email", nullable = false, length = 100)
    private String employeeEmail;

    @Column(name = "employee_phone", nullable = false, length = 50)
    private String employeePhone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_country_id", nullable = false)
    private Country employeeCountry;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_state_id", nullable = false)
    private State employeeState;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_district_id", nullable = false)
    private District employeeDistrict;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_city_id", nullable = false)
    private City employeeCity;

    @Column(name = "employee_street_1", nullable = false, length = 300)
    private String employeeStreet1;

    @Column(name = "employee_street_2", length = 300)
    private String employeeStreet2;

    @Column(name = "employee_street_zipcode", length = 50)
    private String employeeStreetZipcode;

    @Column(name = "employee_description", length = 300)
    private String employeeDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_credentials_id")
    private Credentials employeeCredentials;

}