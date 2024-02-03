package com.capstonelegal.common.model.entities;
import com.capstonelegal.common.model.entities.Country;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import javax.persistence.*;

@Entity
@Table(name = "STATE", schema = "capstonelegalschema")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class State {

    @Id
    @Column(name = "STATE_ID")
    private String stateId;

    @Column(name = "STATE_NAME", nullable = false)
    private String stateName;

    @Column(name = "STATE_INITIALS", nullable = false)
    private String stateInitials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    @JsonIgnore
    private Country country;

    public State() {}

    public State(String stateId, String stateName, String stateInitials, Country country) {
        this.stateId = stateId;
        this.stateName = stateName;
        this.stateInitials = stateInitials;
        this.country = country;
    }

    /**
     * Get the state ID.
     *
     * @return the state ID
     */
    public String getStateId() {
        return stateId;
    }

    /**
     * Set the state ID.
     *
     * @param stateId the state ID
     */
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    /**
     * Get the state name.
     *
     * @return the state name
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * Set the state name.
     *
     * @param stateName the state name
     */
    public void
    setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * Get the state initials.
     *
     * @return the state initials
     */
    public String getStateInitials() {
        return stateInitials;
    }

    /**
     * Set the state initials.
     *
     * @param stateInitials the state initials
     */
    public void setStateInitials(String stateInitials) {
        this.stateInitials = stateInitials;
    }

    /**
     * Get the country.
     *
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Set the country.
     *
     * @param country the country
     */
    public void setCountry(Country country) {
        this.country = country;
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