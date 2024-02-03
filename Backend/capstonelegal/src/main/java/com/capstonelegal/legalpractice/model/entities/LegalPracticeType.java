package com.capstonelegal.legalpractice.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "legal_practice_type", schema = "capstonelegalschema")
public class LegalPracticeType {
    @Id
    @Column(name = "legal_practice_type_id", nullable = false, length = 50)
    private String legalPracticeTypeId;

    @Column(name = "legal_practice_type_name", nullable = false, length = 100)
    private String legalPracticeTypeName;

    @Column(name = "legal_practice_type_description", nullable = false, length = 300)
    private String legalPracticeTypeDescription;

}