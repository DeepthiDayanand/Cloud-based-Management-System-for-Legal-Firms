package com.capstonelegal.department.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "department", schema = "capstonelegalschema")
public class Department {
    @Id
    @Column(name = "department_id", nullable = false, length = 50)
    private String departmentId;

    @Column(name = "department_name", nullable = false, length = 100)
    private String departmentName;

    @Column(name = "department_description", nullable = false, length = 300)
    private String departmentDescription;

}