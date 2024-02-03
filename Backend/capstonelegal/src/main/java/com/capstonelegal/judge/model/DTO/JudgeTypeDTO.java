package com.capstonelegal.judge.model.DTO;

import com.capstonelegal.judge.model.entities.JudgeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link com.capstonelegal.judge.model.entities.JudgeType}
 */
// @Data is a Lombok-provided annotation which tells Lombok to generate
// a getter, setter, equals, hashCode, toString methods, and a constructor for all final fields.
@Data

// @AllArgsConstructor is an annotation provided by Lombok
// which generates a constructor with 1 parameter for each field in your class.
@AllArgsConstructor

// @NoArgsConstructor is a Lombok-provided annotation which generates a no-args constructor.
@NoArgsConstructor

// @Accessors is a Lombok-provided annotation which can be configured
// to make setters return 'this' (the setter's class). 'chain' is set to true
// to allow for "method chaining" or "fluent-style" programming.
@Accessors(chain = true)

// @JsonIgnoreProperties is a Jackson annotation which indicates that other fields of the JSON
// data should be ignored and not included in the DTO during deserialization if they're not found in the DTO.
// 'ignoreUnknown' is set to true to ignore any unrecognized properties during deserialization.
@JsonIgnoreProperties(ignoreUnknown = true)
public class JudgeTypeDTO implements Serializable {
    private String judgeTypeId;
    private String judgeTypeName;
    private String judgeTypeDescription;
    public static JudgeTypeDTO fromJudgeTypeEntity(JudgeType judgeType) {
        return new JudgeTypeDTO()
                .setJudgeTypeId(judgeType.getJudgeTypeId())
                .setJudgeTypeName(judgeType.getJudgeTypeName())
                .setJudgeTypeDescription(judgeType.getJudgeTypeDescription());
    }
}