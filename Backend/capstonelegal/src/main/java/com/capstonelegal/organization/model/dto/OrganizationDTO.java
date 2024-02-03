package com.capstonelegal.organization.model.dto;

import com.capstonelegal.common.model.dto.CityDTO;
import com.capstonelegal.common.model.dto.CountryDTO;
import com.capstonelegal.common.model.dto.DistrictDTO;
import com.capstonelegal.common.model.dto.StateDTO;
import com.capstonelegal.organization.model.entities.Organization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link Organization}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationDTO implements Serializable {
    private String organizationId;
    private String organizationName;
    private String organizationDescription;
    private String organizationEmail;
    private String organizationPhone;
    private String organizationStreet1;
    private String organizationStreet2;
    private String organizationStreetZipcode;
    private String organizationCityId;
    private String organizationCityName;
    private String organizationDistrictId;
    private String organizationDistrictName;
    private String organizationStateId;
    private String organizationStateName;
    private String organizationCountryId;
    private String organizationCountryName;



    /**
     * Converts an Organization entity object into an OrganizationDTO object
     *
     * @param organization The Organization entity that will be converted into an OrganizationDTO
     * @return The converted OrganizationDTO object
     */
    public static OrganizationDTO fromEntity(Organization organization) {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setOrganizationId(organization.getOrganizationId());
        organizationDTO.setOrganizationName(organization.getOrganizationName());
        organizationDTO.setOrganizationDescription(organization.getOrganizationDescription());
        organizationDTO.setOrganizationEmail(organization.getOrganizationEmail());
        organizationDTO.setOrganizationPhone(organization.getOrganizationPhone());

        if (organization.getOrganizationCountry() != null) {
            organizationDTO.setOrganizationCountryId(organization.getOrganizationCountry().getCountryId());
            organizationDTO.setOrganizationCountryName(organization.getOrganizationCountry().getCountryName());
        }
        if (organization.getOrganizationState() != null) {
            organizationDTO.setOrganizationStateId(organization.getOrganizationState().getStateId());
            organizationDTO.setOrganizationStateName(organization.getOrganizationState().getStateName());
        }
        if (organization.getOrganizationDistrict() != null) {
            organizationDTO.setOrganizationDistrictId(organization.getOrganizationDistrict().getDistrictId());
            organizationDTO.setOrganizationDistrictName(organization.getOrganizationDistrict().getDistrictName());
        }
        if (organization.getOrganizationCity() != null) {
            organizationDTO.setOrganizationCityId(organization.getOrganizationCity().getCityId());
            organizationDTO.setOrganizationCityName(organization.getOrganizationCity().getCityName());
        }

        organizationDTO.setOrganizationStreet1(organization.getOrganizationStreet1());
        organizationDTO.setOrganizationStreet2(organization.getOrganizationStreet2());
        organizationDTO.setOrganizationStreetZipcode(organization.getOrganizationStreetZipcode());

        return organizationDTO;
    }
}
