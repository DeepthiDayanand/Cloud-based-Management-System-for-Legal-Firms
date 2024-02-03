package com.capstonelegal.judge.model.entities;
import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.model.entities.District;
import com.capstonelegal.common.model.entities.State;
import com.capstonelegal.court.model.entities.Court;
import com.capstonelegal.credentials.model.entities.Credentials;
import com.google.gson.Gson;

import javax.persistence.*;

@Entity
@Table(name = "JUDGE", schema = "capstonelegalschema")
public class Judge {

    @Id
    @Column(name = "JUDGE_ID")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JUDGE_TYPE_ID")
    private JudgeType judgeType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JUDGE_COURT_ID")
    private Court court;

    @Column(name = "JUDGE_FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "JUDGE_LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "JUDGE_EMAIL", nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JUDGE_COUNTRY_ID", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JUDGE_STATE_ID", nullable = false)
    private State state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JUDGE_DISTRICT_ID", nullable = false)
    private District district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JUDGE_CITY_ID", nullable = false)
    private City city;

    @Column(name = "JUDGE_STREET_1", nullable = false)
    private String street1;

    @Column(name = "JUDGE_STREET_2")
    private String street2;

    @Column(name = "JUDGE_STREET_ZIPCODE")
    private String zipcode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTACT_CREDENTIALS_ID")
    private Credentials credentials;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JudgeType getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(JudgeType judgeType) {
        this.judgeType = judgeType;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
    public String toJSON() {
        return new Gson().toJson(this);
    }
}
