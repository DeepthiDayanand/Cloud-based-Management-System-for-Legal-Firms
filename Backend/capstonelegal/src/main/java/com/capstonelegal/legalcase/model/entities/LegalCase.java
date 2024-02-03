package com.capstonelegal.legalcase.model.entities;

import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.model.entities.District;
import com.capstonelegal.common.model.entities.State;
import com.capstonelegal.legalcase.model.dto.LegalCaseDTO;
import com.capstonelegal.organization.model.dto.OrganizationDTO;
import com.capstonelegal.organization.model.entities.Organization;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "legal_case", schema = "capstonelegalschema")
public class LegalCase {
    @Id
    @Column(name = "legal_case_id", nullable = false, length = 50)
    private String legalCaseId;

    @Column(name = "legal_case_title", nullable = false, length = 100)
    private String legalCaseTitle;

    @Column(name = "legal_case_description", nullable = false, length = 1000)
    private String legalCaseDescription;

    @Column(name = "legal_case_status", nullable = false, length = 50)
    private String legalCaseStatus;

    @Column(name = "legal_case_document")
    private byte[] legalCaseDocument;

    @Column(name = "legal_case_date")
    private LocalDate legalCaseDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "legal_case_organization_id", nullable = false)
    private Organization legalCaseOrganizationId;

    public static LegalCase fromDTO(LegalCaseDTO legalCaseDTO) {
        LegalCase legalCase = new LegalCase();

        legalCase.setLegalCaseId(legalCaseDTO.getLegalCaseId());
        legalCase.setLegalCaseTitle(legalCaseDTO.getLegalCaseTitle());
        legalCase.setLegalCaseDescription(legalCaseDTO.getLegalCaseDescription());
        legalCase.setLegalCaseStatus(legalCaseDTO.getLegalCaseStatus());

        legalCase.setLegalCaseDocument(legalCaseDTO.getLegalCaseDocument());
        legalCase.setLegalCaseDate(legalCaseDTO.getLegalCaseDate());


        if (legalCaseDTO.getLegalCaseOrganizationId() != null) {
            Organization organization = new Organization();
            organization.setOrganizationId(legalCaseDTO.getLegalCaseOrganizationId());
            legalCase.setLegalCaseOrganizationId(organization);
        }

//        if (organizationDTO.getOrganizationStateId() != null) {
//            State state = new State();
//            state.setStateId(organizationDTO.getOrganizationStateId());
//            organization.setOrganizationState(state);
//        }
//        if (organizationDTO.getOrganizationDistrictId() != null) {
//            District district = new District();
//            district.setDistrictId(organizationDTO.getOrganizationDistrictId());
//            organization.setOrganizationDistrict(district);
//        }
//        if (organizationDTO.getOrganizationCityId() != null) {
//            City city = new City();
//            city.setCityId(organizationDTO.getOrganizationCityId());
//            organization.setOrganizationCity(city);
//        }
//
//        organization.setOrganizationStreet1(organizationDTO.getOrganizationStreet1());
//        organization.setOrganizationStreet2(organizationDTO.getOrganizationStreet2());
//        organization.setOrganizationStreetZipcode(organizationDTO.getOrganizationStreetZipcode());

        return legalCase;
    }


}

