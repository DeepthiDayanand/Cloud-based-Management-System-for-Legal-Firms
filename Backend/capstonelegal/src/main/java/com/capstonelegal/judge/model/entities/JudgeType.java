package com.capstonelegal.judge.model.entities;
import com.google.gson.Gson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "judge_type")
public class JudgeType {

    @Id
    @Column(name = "judge_type_id", length = 50)
    private String judgeTypeId;

    @Column(name = "judge_type_name", length = 100, nullable = false)
    private String judgeTypeName;

    @Column(name = "judge_type_description", length = 300, nullable = false)
    private String judgeTypeDescription;

    // Getters and setters

    public String getJudgeTypeId() {
        return judgeTypeId;
    }

    public void setJudgeTypeId(String judgeTypeId) {
        this.judgeTypeId = judgeTypeId;
    }

    public String getJudgeTypeName() {
        return judgeTypeName;
    }

    public void setJudgeTypeName(String judgeTypeName) {
        this.judgeTypeName = judgeTypeName;
    }

    public String getJudgeTypeDescription() {
        return judgeTypeDescription;
    }

    public void setJudgeTypeDescription(String judgeTypeDescription) {
        this.judgeTypeDescription = judgeTypeDescription;
    }
    public String toJSON() {
        return new Gson().toJson(this);
    }
}