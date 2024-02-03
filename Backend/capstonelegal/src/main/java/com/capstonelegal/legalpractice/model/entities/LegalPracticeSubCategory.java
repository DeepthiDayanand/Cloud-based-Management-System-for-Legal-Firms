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
@Table(name = "legal_practice_sub_category", schema = "capstonelegalschema")
public class LegalPracticeSubCategory {
    @Id
    @Column(name = "legal_practice_sub_category_id", nullable = false, length = 50)
    private String legalPracticeSubCategoryId;

    @Column(name = "legal_practice_sub_category_name", nullable = false, length = 100)
    private String legalPracticeSubCategoryName;

    @Column(name = "legal_practice_sub_category_description", nullable = false, length = 300)
    private String legalPracticeSubCategoryDescription;

}