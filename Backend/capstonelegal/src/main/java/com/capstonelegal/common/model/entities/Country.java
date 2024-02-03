package com.capstonelegal.common.model.entities;

import com.google.gson.Gson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY", schema = "capstonelegalschema")
public class Country {

    @Id
    @Column(name = "COUNTRY_ID")
    private String countryId;

    @Column(name = "COUNTRY_NAME", nullable = false)
    private String countryName;

    @Column(name = "COUNTRY_INITIALS", nullable = false)
    private String countryInitials;

    public Country() {}

    public Country(String countryId, String countryName, String countryInitials) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.countryInitials = countryInitials;
    }

    /**
     * Get the country ID.
     *
     * @return the country ID
     */
    public String getCountryId() {
        return countryId;
    }

    /**
     * Set the country ID.
     *
     * @param countryId the country ID
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    /**
     * Get the country name.
     *
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Set the country name.
     *
     * @param countryName the country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Get the country initials.
     *
     * @return the country initials
     */
    public String getCountryInitials() {
        return countryInitials;
    }

    /**
     * Set the country initials.
     *
     * @param countryInitials the country initials
     */
    public void setCountryInitials(String countryInitials) {
        this.countryInitials = countryInitials;
    }

    /**
     * Convert the object to a JSON string.
     *
     * @return the JSON string representation of the object
     */
    public String toJSON() {
        return new Gson().toJson(this);
    }
}
