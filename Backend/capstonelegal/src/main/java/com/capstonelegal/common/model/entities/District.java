package com.capstonelegal.common.model.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import javax.persistence.*;
@Entity
@Table(name = "DISTRICT", schema = "capstonelegalschema")
public class District {
    @Id
    @Column(name = "DISTRICT_ID")
    private String districtId;

    @Column(name = "DISTRICT_NAME", nullable = false)
    private String districtName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATE_ID", nullable = false)
    @JsonIgnore
    private State state;

    public District() {}

    public District(String districtId, String districtName, State state) {
        this.districtId = districtId;
        this.districtName = districtName;
        this.state = state;
    }

    /**
     * Get the district ID.
     *
     * @return the district ID
     */
    public String getDistrictId() {
        return districtId;
    }

    /**
     * Set the district ID.
     *
     * @param districtId the district ID
     */
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    /**
     * Get the district name.
     *
     * @return the district name
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * Set the district name.
     *
     * @param districtName the district name
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * Get the state.
     *
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Set the state.
     *
     * @param state the state
     */
    public void setState(State state) {
        this.state = state;
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
