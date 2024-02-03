package com.capstonelegal.court.model.entities;
import com.google.gson.Gson;

import javax.persistence.*;

@Entity
@Table(name = "COURT_TYPE", schema = "capstonelegalschema")
public class CourtType {

    @Id
    @Column(name = "COURT_TYPE_ID")
    private String courtTypeId;

    @Column(name = "COURT_TYPE_NAME", nullable = false)
    private String courtTypeName;

    @Column(name = "COURT_TYPE_DESCRIPTION", nullable = false)
    private String courtTypeDescription;

    public CourtType() {
    }

    public CourtType(String courtTypeId, String courtTypeName, String courtTypeDescription) {
        this.courtTypeId = courtTypeId;
        this.courtTypeName = courtTypeName;
        this.courtTypeDescription = courtTypeDescription;
    }

    public String getCourtTypeId() {
        return courtTypeId;
    }

    public void setCourtTypeId(String courtTypeId) {
        this.courtTypeId = courtTypeId;
    }

    public String getCourtTypeName() {
        return courtTypeName;
    }

    public void setCourtTypeName(String courtTypeName) {
        this.courtTypeName = courtTypeName;
    }

    public String getCourtTypeDescription() {
        return courtTypeDescription;
    }

    public void setCourtTypeDescription(String courtTypeDescription) {
        this.courtTypeDescription = courtTypeDescription;
    }

    public String toJSON() {
        return new Gson().toJson(this);
    }
}

