package com.capstonelegal.organization.model.entities;

import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.model.entities.District;
import com.capstonelegal.common.model.entities.State;
import com.capstonelegal.organization.model.dto.OrganizationDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "organization", schema = "capstonelegalschema")
public class Organization {
    @Id
    @Column(name = "organization_id", nullable = false, length = 50)
    private String organizationId;

    @Column(name = "organization_name", nullable = false, length = 100)
    private String organizationName;

    @Column(name = "organization_description", nullable = false, length = 300)
    private String organizationDescription;

    @Column(name = "organization_email", nullable = false, length = 100)
    private String organizationEmail;

    @Column(name = "organization_phone", nullable = false, length = 50)
    private String organizationPhone;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organization_country_id", nullable = false)
    private Country organizationCountry;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organization_state_id", nullable = false)
    private State organizationState;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organization_district_id", nullable = false)
    private District organizationDistrict;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organization_city_id", nullable = false)
    private City organizationCity;

    @Column(name = "organization_street_1", nullable = false, length = 300)
    private String organizationStreet1;

    @Column(name = "organization_street_2", length = 300)
    private String organizationStreet2;

    @Column(name = "organization_street_zipcode", length = 50)
    private String organizationStreetZipcode;

    /**
     * Converts an Organization DTO object into an Organization Entity object
     *
     * @param organizationDTO The Organization DTO that will be converted into an Organization Entity
     * @return The converted OrganizationDTO object
     */
    public static Organization fromDTO(OrganizationDTO organizationDTO) {
        Organization organization = new Organization();
        organization.setOrganizationId(organizationDTO.getOrganizationId());
        organization.setOrganizationName(organizationDTO.getOrganizationName());
        organization.setOrganizationDescription(organizationDTO.getOrganizationDescription());
        organization.setOrganizationEmail(organizationDTO.getOrganizationEmail());
        organization.setOrganizationPhone(organizationDTO.getOrganizationPhone());

        if (organizationDTO.getOrganizationCountryId() != null) {
            Country country = new Country();
            country.setCountryId(organizationDTO.getOrganizationCountryId());
            organization.setOrganizationCountry(country);
        }
        if (organizationDTO.getOrganizationStateId() != null) {
            State state = new State();
            state.setStateId(organizationDTO.getOrganizationStateId());
            organization.setOrganizationState(state);
        }
        if (organizationDTO.getOrganizationDistrictId() != null) {
            District district = new District();
            district.setDistrictId(organizationDTO.getOrganizationDistrictId());
            organization.setOrganizationDistrict(district);
        }
        if (organizationDTO.getOrganizationCityId() != null) {
            City city = new City();
            city.setCityId(organizationDTO.getOrganizationCityId());
            organization.setOrganizationCity(city);
        }

        organization.setOrganizationStreet1(organizationDTO.getOrganizationStreet1());
        organization.setOrganizationStreet2(organizationDTO.getOrganizationStreet2());
        organization.setOrganizationStreetZipcode(organizationDTO.getOrganizationStreetZipcode());

        return organization;
    }

}