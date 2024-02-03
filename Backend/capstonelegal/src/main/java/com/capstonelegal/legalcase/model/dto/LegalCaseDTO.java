package com.capstonelegal.legalcase.model.dto;

import com.capstonelegal.legalcase.model.entities.LegalCase;
import com.capstonelegal.organization.model.dto.OrganizationDTO;
import com.capstonelegal.organization.model.entities.Organization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link LegalCase}
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
public class LegalCaseDTO implements Serializable {
    private String legalCaseId;
    private String legalCaseTitle;
    private String legalCaseDescription;
    private String legalCaseStatus;
    private byte[] legalCaseDocument;
    private LocalDate legalCaseDate;
    private String legalCaseOrganizationId;



    /**
     * Converts an LegalCase entity object into an LegalCaseDTO object
     *
     * @param legalCase The LegalCase entity that will be converted into an LegalCaseDTO
     * @return The converted LegalCaseDTO object
     */
    public static LegalCaseDTO fromEntity(LegalCase legalCase) {
        LegalCaseDTO legalCaseDTO = new LegalCaseDTO();

        legalCaseDTO.setLegalCaseId(legalCase.getLegalCaseId());
        legalCaseDTO.setLegalCaseTitle(legalCase.getLegalCaseTitle());
        legalCaseDTO.setLegalCaseDescription(legalCase.getLegalCaseDescription());
        legalCaseDTO.setLegalCaseStatus(legalCase.getLegalCaseStatus());

        legalCaseDTO.setLegalCaseDocument(legalCase.getLegalCaseDocument());
        legalCaseDTO.setLegalCaseDate(legalCase.getLegalCaseDate());


        if (legalCase.getLegalCaseOrganizationId() != null) {
            legalCaseDTO.setLegalCaseOrganizationId(legalCase.getLegalCaseOrganizationId().getOrganizationId());
//            legalCaseDTO.setLegalCaseOrganizationId(legalCase.getLegalCaseOrganizationId().getOrganizationId());
        }

        return legalCaseDTO;
    }
}
