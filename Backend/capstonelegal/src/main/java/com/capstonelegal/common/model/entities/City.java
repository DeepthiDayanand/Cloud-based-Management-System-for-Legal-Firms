package com.capstonelegal.common.model.entities;
import com.google.gson.Gson;
import javax.persistence.*;
@Entity
@Table(name = "CITY", schema = "capstonelegalschema")
public class City {
    @Id
    @Column(name = "CITY_ID")
    private String cityId;

    @Column(name = "CITY_NAME", nullable = false)
    private String cityName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DISTRICT_ID", nullable = false)
    private District district;

    public City() {}

    public City(String cityId, String cityName, District district) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.district = district;
    }
    /**
     * Get the city ID.
     *
     * @return the city ID
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * Set the city ID.
     *
     * @param cityId the city ID
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * Get the city name.
     *
     * @return the city name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Set the city name.
     *
     * @param cityName the city name
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Get the district.
     *
     * @return the district
     */
    public District getDistrict() {
        return district;
    }

    /**
     * Set the district.
     *
     * @param district the district
     */
    public void setDistrict(District district) {
        this.district = district;
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
