package com.capstonelegal.judge.model.entities;
import javax.persistence.*;

@Entity
@Table(name = "JUDGE_VIEW", schema = "capstonelegalschema")
public class JudgeView {

    @Id
    @Column(name = "JUDGE_ID")
    private String judgeId;

    @Column(name = "JUDGE_FIRST_NAME")
    private String judgeFirstName;

    @Column(name = "JUDGE_LAST_NAME")
    private String judgeLastName;

    @Column(name = "JUDGE_TYPE_NAME")
    private String judgeTypeName;

    @Column(name = "JUDGE_EMAIL")
    private String judgeEmail;

    @Column(name = "COURT_TYPE_NAME")
    private String courtTypeName;

    @Column(name = "JUDGE_STREET_1")
    private String judgeStreet1;

    @Column(name = "JUDGE_STREET_2")
    private String judgeStreet2;

    @Column(name = "JUDGE_CITY_NAME")
    private String judgeCityName;

    @Column(name = "JUDGE_DISTRICT_NAME")
    private String judgeDistrictName;

    @Column(name = "JUDGE_STATE_NAME")
    private String judgeStateName;

    @Column(name = "JUDGE_COUNTRY_NAME")
    private String judgeCountryName;

    public String getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(String judgeId) {
        this.judgeId = judgeId;
    }

    public String getJudgeFirstName() {
        return judgeFirstName;
    }

    public void setJudgeFirstName(String judgeFirstName) {
        this.judgeFirstName = judgeFirstName;
    }

    public String getJudgeLastName() {
        return judgeLastName;
    }

    public void setJudgeLastName(String judgeLastName) {
        this.judgeLastName = judgeLastName;
    }

    public String getJudgeTypeName() {
        return judgeTypeName;
    }

    public void setJudgeTypeName(String judgeTypeName) {
        this.judgeTypeName = judgeTypeName;
    }

    public String getJudgeEmail() {
        return judgeEmail;
    }

    public void setJudgeEmail(String judgeEmail) {
        this.judgeEmail = judgeEmail;
    }

    public String getCourtTypeName() {
        return courtTypeName;
    }

    public void setCourtTypeName(String courtTypeName) {
        this.courtTypeName = courtTypeName;
    }

    public String getJudgeStreet1() {
        return judgeStreet1;
    }

    public void setJudgeStreet1(String judgeStreet1) {
        this.judgeStreet1 = judgeStreet1;
    }

    public String getJudgeStreet2() {
        return judgeStreet2;
    }

    public void setJudgeStreet2(String judgeStreet2) {
        this.judgeStreet2 = judgeStreet2;
    }

    public String getJudgeCityName() {
        return judgeCityName;
    }

    public void setJudgeCityName(String judgeCityName) {
        this.judgeCityName = judgeCityName;
    }

    public String getJudgeDistrictName() {
        return judgeDistrictName;
    }

    public void setJudgeDistrictName(String judgeDistrictName) {
        this.judgeDistrictName = judgeDistrictName;
    }

    public String getJudgeStateName() {
        return judgeStateName;
    }

    public void setJudgeStateName(String judgeStateName) {
        this.judgeStateName = judgeStateName;
    }

    public String getJudgeCountryName() {
        return judgeCountryName;
    }

    public void setJudgeCountryName(String judgeCountryName) {
        this.judgeCountryName = judgeCountryName;
    }

}
