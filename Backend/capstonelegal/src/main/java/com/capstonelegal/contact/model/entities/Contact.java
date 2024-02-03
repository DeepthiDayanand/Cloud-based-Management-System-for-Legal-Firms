package com.capstonelegal.contact.model.entities;

import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.model.entities.District;
import com.capstonelegal.common.model.entities.State;
import com.capstonelegal.credentials.model.entities.Credentials;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "contact", schema = "capstonelegalschema")
public class Contact {
    @Id
    @Column(name = "contact_id", nullable = false, length = 50)
    private String contactId;

    @Column(name = "contact_first_name", nullable = false, length = 100)
    private String contactFirstName;

    @Column(name = "contact_last_name", nullable = false, length = 100)
    private String contactLastName;

    @Column(name = "contact_email", nullable = false, length = 100)
    private String contactEmail;

    @Column(name = "contact_phone", nullable = false, length = 50)
    private String contactPhone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_country_id", nullable = false)
    private Country contactCountry;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_state_id", nullable = false)
    private State contactState;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_district_id", nullable = false)
    private District contactDistrict;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_city_id", nullable = false)
    private City contactCity;

    @Column(name = "contact_street_1", nullable = false, length = 300)
    private String contactStreet1;

    @Column(name = "contact_street_2", length = 300)
    private String contactStreet2;

    @Column(name = "contact_street_zipcode", length = 50)
    private String contactStreetZipcode;

    @Column(name = "contact_description", length = 300)
    private String contactDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_credentials_id")
    private Credentials contactCredentials;

}