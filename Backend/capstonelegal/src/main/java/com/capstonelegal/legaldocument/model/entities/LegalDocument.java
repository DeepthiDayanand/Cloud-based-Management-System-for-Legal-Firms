package com.capstonelegal.legaldocument.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "legal_document", schema = "capstonelegalschema")
public class LegalDocument {
    @Id
    @Column(name = "legal_document_id", nullable = false, length = 50)
    private String legalDocumentId;

    @Column(name = "legal_document_title", nullable = false, length = 100)
    private String legalDocumentTitle;

    @Column(name = "legal_document_description", nullable = false, length = 1000)
    private String legalDocumentDescription;

    @Column(name = "legal_document_status", nullable = false, length = 50)
    private String legalDocumentStatus;

    @Column(name = "legal_document_secure_dek", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String legalDocumentSecureDek;

}