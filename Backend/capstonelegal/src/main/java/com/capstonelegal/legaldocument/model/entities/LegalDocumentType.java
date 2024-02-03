package com.capstonelegal.legaldocument.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "legal_document_type", schema = "capstonelegalschema")
public class LegalDocumentType {
    @Id
    @Column(name = "legal_document_type_id", nullable = false, length = 50)
    private String legalDocumentTypeId;

    @Column(name = "legal_document_type_name", nullable = false, length = 100)
    private String legalDocumentTypeName;

    @Column(name = "legal_document_type_description", nullable = false, length = 300)
    private String legalDocumentTypeDescription;

}