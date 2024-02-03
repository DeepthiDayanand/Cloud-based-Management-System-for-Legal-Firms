package com.capstonelegal.organization.service;

import com.capstonelegal.common.repository.CityRepository;
import com.capstonelegal.common.repository.CountryRepository;
import com.capstonelegal.common.repository.DistrictRepository;
import com.capstonelegal.common.repository.StateRepository;
import com.capstonelegal.organization.model.dto.OrganizationDTO;
import com.capstonelegal.organization.model.entities.Organization;
import com.capstonelegal.organization.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capstonelegal.common.exception.ResourceNotFoundException;
import com.capstonelegal.common.model.mapper.*;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.model.entities.District;
import com.capstonelegal.common.model.entities.State;
/**
 * Service class that handles business logic related to organizations.
 */
@Service
@Transactional
@Slf4j
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    /**
     * Constructor to autowire CityRepository, DistrictRepository, StateRepository, and CountryRepository.
     * @param organizationRepository Autowired OrganizationRepository to handle Organization related operations.
     * @param modelMapper Autowired ModelMapper to handle mapping related operations.
     * @param cityRepository Autowired CityRepository to handle city related operations.
     * @param districtRepository Autowired DistrictRepository to handle district related operations.
     * @param stateRepository Autowired StateRepository to handle state related operations.
     * @param countryRepository Autowired CountryRepository to handle country related operations.
     */
    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, ModelMapper modelMapper, CityRepository cityRepository, DistrictRepository districtRepository, StateRepository stateRepository, CountryRepository countryRepository) {
        this.organizationRepository = organizationRepository;
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;

    }

    /**
     * Fetches all organizations with optional filtering.
     * @param organizationName Optional Organization name to filter cities.
     * @param countryName Optional country name to filter cities.
     * @param stateName Optional state name to filter cities.
     * @param districtName Optional district name to filter cities.
     * @param cityName Optional city name to filter cities.
     * @param pageable Pageable object for pagination.
     * @return Page of OrganizationDTO.
     */
    public Page<OrganizationDTO> getOrganizationsByFilters(Pageable pageable, String organizationName, String countryName, String stateName, String districtName, String cityName) {
        log.info("Fetching Organizations with filters - organization name: {}. country name: {}, state name: {}, district name: {}, city name: {}, page: {}, size: {}", organizationName, countryName, stateName, districtName, cityName, pageable.getPageNumber(), pageable.getPageSize());
        // Use Specification to create custom query
        Page<Organization> organizations = organizationRepository.findAll((Specification<Organization>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtering by country name
            if (countryName != null && !countryName.isEmpty()) {
                Join<Organization, City> cityJoin = root.join("city");
                Join<City, District> districtJoin = root.join("district");
                Join<District, State> stateJoin = districtJoin.join("state");
                Join<State, Country> countryJoin = stateJoin.join("country");
                predicates.add(criteriaBuilder.like(countryJoin.get("countryName"), "%" + countryName + "%"));
            }

            // Filtering by state name
            if (stateName != null && !stateName.isEmpty()) {
                Join<Organization, District> districtJoin = root.join("district");
                Join<District, State> stateJoin = districtJoin.join("state");
                predicates.add(criteriaBuilder.like(stateJoin.get("stateName"), "%" + stateName + "%"));
            }

            // Filtering by district name
            if (districtName != null && !districtName.isEmpty()) {
                Join<Organization, District> districtJoin = root.join("district");
                predicates.add(criteriaBuilder.like(districtJoin.get("districtName"), "%" + districtName + "%"));
            }

            // Filtering by city name
            if (cityName != null && !cityName.isEmpty()) {
                Join<Organization, City> districtJoin = root.join("district");
                predicates.add(criteriaBuilder.like(root.get("cityName"), "%" + cityName + "%"));
            }
            // Filtering by city name
            if (organizationName != null && !organizationName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("organizationName"), "%" + organizationName + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return organizations.map(OrganizationDTO::fromEntity);    }

    /**
     * Creates a new organization.
     *
     * @param organizationDTO The DTO containing organization details
     * @return The created OrganizationDTO
     */
    public OrganizationDTO createOrganization(OrganizationDTO organizationDTO) {
        log.info("Creating a new organization");
        Organization organization = convertToEntity(organizationDTO);
        Organization savedOrganization = organizationRepository.save(organization);
        return convertToDto(savedOrganization);
    }

    /**
     * Updates an existing organization.
     *
     * @param organizationId  The ID of the organization to update
     * @param organizationDTO The DTO containing updated organization details
     * @return The updated OrganizationDTO
     * @throws ResourceNotFoundException If the organization is not found
     */
    public OrganizationDTO updateOrganization(String organizationId, OrganizationDTO organizationDTO) {
        log.info("Updating organization with ID: {}", organizationId);
        Organization existingOrganization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + organizationId));

        Organization updatedOrganization = updateEntityFromDto(existingOrganization, organizationDTO);
        Organization savedOrganization = organizationRepository.save(updatedOrganization);
        return convertToDto(savedOrganization);
    }

    /**
     * Fetches details of a specific organization.
     *
     * @param organizationId The ID of the organization to fetch
     * @return The OrganizationDTO containing organization details
     * @throws ResourceNotFoundException If the organization is not found
     */
    public OrganizationDTO viewOrganization(String organizationId) {
        log.info("Fetching details of organization with ID: {}", organizationId);
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + organizationId));
        return convertToDto(organization);
    }

    /**
     * Deletes an organization.
     *
     * @param organizationId The ID of the organization to delete
     */
    public void deleteOrganization(String organizationId) {
        log.info("Deleting organization with ID: {}", organizationId);
        organizationRepository.deleteById(organizationId);
    }

    /**
     * Converts an Organization entity to an OrganizationDTO.
     *
     * @param organization The Organization entity to convert
     * @return The corresponding OrganizationDTO
     */
    private OrganizationDTO convertToDto(Organization organization) {
        return OrganizationDTO.fromEntity(organization);
    }

    /**
     * Converts an OrganizationDTO to an Organization entity.
     *
     * @param organizationDTO The OrganizationDTO to convert
     * @return The corresponding Organization entity
     */
    private Organization convertToEntity(OrganizationDTO organizationDTO) {
        Organization tempOrganization = Organization.fromDTO(organizationDTO);
        if (organizationDTO.getOrganizationCountryId() != null){
            tempOrganization.setOrganizationCountry(countryRepository.findById(organizationDTO.getOrganizationCountryId()).orElse(null));
        }

        if (organizationDTO.getOrganizationStateId() != null){
            tempOrganization.setOrganizationState(stateRepository.findById(organizationDTO.getOrganizationStateId()).orElse(null));
        }

        if (organizationDTO.getOrganizationDistrictId() != null){
            tempOrganization.setOrganizationDistrict(districtRepository.findById(organizationDTO.getOrganizationDistrictId()).orElse(null));
        }

        if (organizationDTO.getOrganizationCityId() != null){
            tempOrganization.setOrganizationCity(cityRepository.findById(organizationDTO.getOrganizationCityId()).orElse(null));
        }
        return tempOrganization;
    }

    /**
     * Updates an existing Organization entity from an OrganizationDTO.
     *
     * @param organization         The existing Organization entity
     * @param organizationDTO      The OrganizationDTO containing updated details
     * @return The updated Organization entity
     */
    private Organization updateEntityFromDto(Organization organization, OrganizationDTO organizationDTO) {
        organization.setOrganizationName(organizationDTO.getOrganizationName());
        organization.setOrganizationDescription(organizationDTO.getOrganizationDescription());
        organization.setOrganizationEmail(organizationDTO.getOrganizationEmail());
        organization.setOrganizationPhone(organizationDTO.getOrganizationPhone());
        organization.setOrganizationStreet1(organizationDTO.getOrganizationStreet1());
        organization.setOrganizationStreet2(organizationDTO.getOrganizationStreet2());
        organization.setOrganizationStreetZipcode(organizationDTO.getOrganizationStreetZipcode());

        if (organizationDTO.getOrganizationCountryId() != null){
            organization.setOrganizationCountry(countryRepository.findById(organizationDTO.getOrganizationCountryId()).orElse(null));
        }

        if (organizationDTO.getOrganizationStateId() != null){
            organization.setOrganizationState(stateRepository.findById(organizationDTO.getOrganizationStateId()).orElse(null));
        }

        if (organizationDTO.getOrganizationDistrictId() != null){
            organization.setOrganizationDistrict(districtRepository.findById(organizationDTO.getOrganizationDistrictId()).orElse(null));
        }

        if (organizationDTO.getOrganizationCityId() != null){
            organization.setOrganizationCity(cityRepository.findById(organizationDTO.getOrganizationCityId()).orElse(null));
        }

        return organization;
    }
}
