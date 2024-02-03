package com.capstonelegal.court.model.entities;

import javax.persistence.*;

import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.model.entities.District;
import com.capstonelegal.common.model.entities.State;
import com.google.gson.*;

@Entity
@Table(name = "COURT", schema = "capstonelegalschema")
public class Court {
    @Id
    @Column(name = "COURT_ID")
    private String courtId;

    @Column(name = "COURT_TYPE_ID", nullable = false)
    private String courtTypeId;

    @Column(name = "COURT_TYPE_NAME", nullable = false)
    private String courtTypeName;

    @ManyToOne
    @JoinColumn(name = "COURT_COUNTRY_ID", referencedColumnName = "COUNTRY_ID", nullable = false)
    private Country courtCountry;

    @ManyToOne
    @JoinColumn(name = "COURT_STATE_ID", referencedColumnName = "STATE_ID", nullable = false)
    private State courtState;

    @ManyToOne
    @JoinColumn(name = "COURT_DISTRICT_ID", referencedColumnName = "DISTRICT_ID", nullable = false)
    private District courtDistrict;

    @ManyToOne
    @JoinColumn(name = "COURT_CITY_ID", referencedColumnName = "CITY_ID", nullable = false)
    private City courtCity;

    @Column(name = "COURT_STREET_1", nullable = false)
    private String courtStreet1;

    @Column(name = "COURT_STREET_2")
    private String courtStreet2;

    @Column(name = "COURT_STREET_ZIPCODE")
    private String courtStreetZipcode;

    // Getters
    public String getCourtId() {
        return courtId;
    }

    public String getCourtTypeId() {
        return courtTypeId;
    }

    public String getCourtTypeName() {
        return courtTypeName;
    }

    public Country getCourtCountry() {
        return courtCountry;
    }

    public State getCourtState() {
        return courtState;
    }

    public District getCourtDistrict() {
        return courtDistrict;
    }

    public City getCourtCity() {
        return courtCity;
    }

    public String getCourtStreet1() {
        return courtStreet1;
    }

    public String getCourtStreet2() {
        return courtStreet2;
    }

    public String getCourtStreetZipcode() {
        return courtStreetZipcode;
    }

    // Setters
    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }

    public void setCourtTypeId(String courtTypeId) {
        this.courtTypeId = courtTypeId;
    }

    public void setCourtTypeName(String courtTypeName) {
        this.courtTypeName = courtTypeName;
    }

    public void setCourtCountry(Country courtCountry) {
        this.courtCountry = courtCountry;
    }

    public void setCourtState(State courtState) {
        this.courtState = courtState;
    }

    public void setCourtDistrict(District courtDistrict) {
        this.courtDistrict = courtDistrict;
    }

    public void setCourtCity(City courtCity) {
        this.courtCity = courtCity;
    }

    public void setCourtStreet1(String courtStreet1) {
        this.courtStreet1 = courtStreet1;
    }

    public void setCourtStreet2(String courtStreet2) {
        this.courtStreet2 = courtStreet2;
    }

    public void setCourtStreetZipcode(String courtStreetZipcode) {
        this.courtStreetZipcode = courtStreetZipcode;
    }
    public String toJSON() {
        return new Gson().toJson(this);
    }
}

