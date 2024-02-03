package com.capstonelegal.config;

import com.capstonelegal.organization.model.dto.OrganizationDTO;
import com.capstonelegal.organization.model.entities.Organization;
import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.common.model.entities.District;
import com.capstonelegal.common.model.entities.State;
import com.capstonelegal.common.model.entities.Country;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapperConfig class.
 *
 * <p>This configuration class is responsible for configuring the ModelMapper bean used
 * for mapping between DTO and Entity objects.</p>
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Creates and configures the ModelMapper bean.
     *
     * <p>The ModelMapper bean simplifies the conversion between DTO objects and Entity objects.
     * This method defines custom mappings to handle complex nested object mappings and null values gracefully.</p>
     *
     * @return a ModelMapper bean.
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Define mapping from OrganizationDTO to Organization entity.
        modelMapper.createTypeMap(OrganizationDTO.class, Organization.class)
                .addMapping(OrganizationDTO::getOrganizationCityId, (org, id) -> {
                    // Check if the OrganizationCity object is null, if so create a new instance
                    if (org.getOrganizationCity() == null) {
                        org.setOrganizationCity(new City());
                    }
                    org.getOrganizationCity().setCityId((String) id);
                })
                .addMapping(OrganizationDTO::getOrganizationDistrictId, (org, id) -> {
                    if (org.getOrganizationDistrict() == null) {
                        org.setOrganizationDistrict(new District());
                    }
                    org.getOrganizationDistrict().setDistrictId((String) id);
                })
                .addMapping(OrganizationDTO::getOrganizationStateId, (org, id) -> {
                    if (org.getOrganizationState() == null) {
                        org.setOrganizationState(new State());
                    }
                    org.getOrganizationState().setStateId((String) id);
                })
                .addMapping(OrganizationDTO::getOrganizationCountryId, (org, id) -> {
                    if (org.getOrganizationCountry() == null) {
                        org.setOrganizationCountry(new Country());
                    }
                    org.getOrganizationCountry().setCountryId((String) id);
                });

        // Define mapping from Organization entity to OrganizationDTO.
        modelMapper.createTypeMap(Organization.class, OrganizationDTO.class)
                .addMapping(org -> org.getOrganizationCity() != null ? org.getOrganizationCity().getCityId() : null, OrganizationDTO::setOrganizationCityId)
                .addMapping(org -> org.getOrganizationCity() != null ? org.getOrganizationCity().getCityName() : null, OrganizationDTO::setOrganizationCityName)
                .addMapping(org -> org.getOrganizationDistrict() != null ? org.getOrganizationDistrict().getDistrictId() : null, OrganizationDTO::setOrganizationDistrictId)
                .addMapping(org -> org.getOrganizationDistrict() != null ? org.getOrganizationDistrict().getDistrictName() : null, OrganizationDTO::setOrganizationDistrictName)
                .addMapping(org -> org.getOrganizationState() != null ? org.getOrganizationState().getStateId() : null, OrganizationDTO::setOrganizationStateId)
                .addMapping(org -> org.getOrganizationState() != null ? org.getOrganizationState().getStateName() : null, OrganizationDTO::setOrganizationStateName)
                .addMapping(org -> org.getOrganizationCountry() != null ? org.getOrganizationCountry().getCountryId() : null, OrganizationDTO::setOrganizationCountryId)
                .addMapping(org -> org.getOrganizationCountry() != null ? org.getOrganizationCountry().getCountryName() : null, OrganizationDTO::setOrganizationCountryName);


        // Return the configured ModelMapper bean.
        return modelMapper;
    }
}
