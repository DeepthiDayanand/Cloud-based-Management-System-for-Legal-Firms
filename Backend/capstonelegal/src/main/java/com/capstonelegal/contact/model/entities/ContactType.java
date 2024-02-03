package com.capstonelegal.contact.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "contact_type", schema = "capstonelegalschema")
public class ContactType {
    @Id
    @Column(name = "contact_type_id", nullable = false, length = 50)
    private String contactTypeId;

    @Column(name = "contact_type_name", nullable = false, length = 100)
    private String contactTypeName;

    @Column(name = "contact_type_description", nullable = false, length = 300)
    private String contactTypeDescription;

}