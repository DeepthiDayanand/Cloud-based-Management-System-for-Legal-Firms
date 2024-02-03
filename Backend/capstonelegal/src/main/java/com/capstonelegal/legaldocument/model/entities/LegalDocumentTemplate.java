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
@Table(name = "legal_document_template", schema = "capstonelegalschema")
public class LegalDocumentTemplate {
    @Id
    @Column(name = "legal_document_template_id", nullable = false, length = 50)
    private String legalDocumentTemplateId;

    @Column(name = "legal_document_template_name", nullable = false, length = 100)
    private String legalDocumentTemplateName;

    @Column(name = "legal_document_template_document")
    private byte[] legalDocumentTemplateDocument;

    @Column(name = "legal_document_template_description", nullable = false, length = 300)
    private String legalDocumentTemplateDescription;

}