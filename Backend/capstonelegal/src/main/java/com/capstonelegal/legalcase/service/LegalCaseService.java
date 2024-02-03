package com.capstonelegal.legalcase.service;

import com.capstonelegal.legalcase.model.dto.LegalCaseDTO;
import com.capstonelegal.legalcase.model.entities.LegalCase;
import com.capstonelegal.legalcase.repository.LegalCaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capstonelegal.common.exception.ResourceNotFoundException;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class that handles business logic related to organizations.
 */
@Service
@Transactional
@Slf4j
public class LegalCaseService {

    private final LegalCaseRepository legalCaseRepository;
    private final ModelMapper modelMapper;


    /**
     * Constructor to autowire CityRepository, DistrictRepository, StateRepository, and CountryRepository.
     *
     * @param legalCaseRepository  Autowired LegalCaseRepository to handle Case related operations.
     * @param modelMapper Autowired LegalCaseModelMapper to handle mapping related operations.
     * @param modelMapper
     */
    @Autowired
    public LegalCaseService(LegalCaseRepository legalCaseRepository, ModelMapper modelMapper) {
        this.legalCaseRepository = legalCaseRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Fetches all organizations with optional filtering.
     * @param legalCaseTitle Optional Case name to filter cases.
     * @param pageable Pageable object for pagination.
     * @return Page of CaseDTO.
     */
    public Page<LegalCaseDTO> getLegalCasesByFilters(Pageable pageable, String legalCaseTitle) {
        log.info("Fetching Cases with filters - case name: {}", legalCaseTitle, pageable.getPageNumber(), pageable.getPageSize());
        // Use Specification to create custom query
        Page<LegalCase> legalCases = legalCaseRepository.findAll((Specification<LegalCase>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtering by country name
//            if (countryName != null && !countryName.isEmpty()) {
//                Join<Organization, City> cityJoin = root.join("city");
//                Join<City, District> districtJoin = root.join("district");
//                Join<District, State> stateJoin = districtJoin.join("state");
//                Join<State, Country> countryJoin = stateJoin.join("country");
//                predicates.add(criteriaBuilder.like(countryJoin.get("countryName"), "%" + countryName + "%"));
//            }
//
//            // Filtering by state name
//            if (stateName != null && !stateName.isEmpty()) {
//                Join<Organization, District> districtJoin = root.join("district");
//                Join<District, State> stateJoin = districtJoin.join("state");
//                predicates.add(criteriaBuilder.like(stateJoin.get("stateName"), "%" + stateName + "%"));
//            }
//
//            // Filtering by district name
//            if (districtName != null && !districtName.isEmpty()) {
//                Join<Organization, District> districtJoin = root.join("district");
//                predicates.add(criteriaBuilder.like(districtJoin.get("districtName"), "%" + districtName + "%"));
//            }
//
//            // Filtering by city name
//            if (cityName != null && !cityName.isEmpty()) {
//                Join<Organization, City> districtJoin = root.join("district");
//                predicates.add(criteriaBuilder.like(root.get("cityName"), "%" + cityName + "%"));
//            }

            // Filtering by case name
            if (legalCaseTitle != null && !legalCaseTitle.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("legalCaseName"), "%" + legalCaseTitle + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return legalCases.map(LegalCaseDTO::fromEntity);    }

    /**
     * Creates a new case.
     *
     * @param legalCaseDTO The DTO containing case details
     * @return The created LegalCaseDTO
     */
    public LegalCaseDTO createLegalCase(LegalCaseDTO legalCaseDTO) {
        log.info("Creating a new case");
        LegalCase legalCase = convertToEntity(legalCaseDTO);
        LegalCase savedCase = legalCaseRepository.save(legalCase);
        return convertToDto(savedCase);
    }

    /**
     * Updates an existing case.
     *
     * @param legalCaseId  The ID of the case to update
     * @param legalCaseDTO The DTO containing updated case details
     * @return The updated LegalCaseDTO
     * @throws ResourceNotFoundException If the case is not found
     */
    public LegalCaseDTO updateLegalCase(String legalCaseId, LegalCaseDTO legalCaseDTO) {
        log.info("Updating case with ID: {}", legalCaseId);
        LegalCase existingCase = legalCaseRepository.findById(legalCaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Case not found with id: " + legalCaseId));

        LegalCase updatedCase = updateEntityFromDto(existingCase, legalCaseDTO);
        LegalCase savedCase = legalCaseRepository.save(updatedCase);
        return convertToDto(savedCase);
    }

    /**
     * Fetches details of a specific case.
     *
     * @param legalCaseId The ID of the case to fetch
     * @return The LegalCaseDTO containing case details
     * @throws ResourceNotFoundException If the case is not found
     */
    public LegalCaseDTO viewLegalCase(String legalCaseId) {
        log.info("Fetching details of case with ID: {}", legalCaseId);
        LegalCase legalCase = legalCaseRepository.findById(legalCaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Case not found with id: " + legalCaseId));
        return convertToDto(legalCase);
    }

    /**
     * Deletes a case.
     *
     * @param legalCaseId The ID of the case to delete
     */
    public void deleteLegalCase(String legalCaseId) {
        log.info("Deleting case with ID: {}", legalCaseId);
        legalCaseRepository.deleteById(legalCaseId);
    }

    /**
     * Converts a Case entity to an CaseDTO.
     *
     * @param legalCase The Case entity to convert
     * @return The corresponding CaseDTO
     */
    private LegalCaseDTO convertToDto(LegalCase legalCase) {
        return LegalCaseDTO.fromEntity(legalCase);
    }

    /**
     * Converts an CaseDTO to a Case entity.
     *
     * @param legalCaseDTO The CaseDTO to convert
     * @return The corresponding Case entity
     */
    private LegalCase convertToEntity(LegalCaseDTO legalCaseDTO) {
        LegalCase tempCase = LegalCase.fromDTO(legalCaseDTO);
//        if (caseDTO.getCaseCountryId() != null){
//            tempOrganization.setOrganizationCountry(countryRepository.findById(organizationDTO.getOrganizationCountryId()).orElse(null));
//        }
//
//        if (organizationDTO.getOrganizationStateId() != null){
//            tempOrganization.setOrganizationState(stateRepository.findById(organizationDTO.getOrganizationStateId()).orElse(null));
//        }
//
//        if (organizationDTO.getOrganizationDistrictId() != null){
//            tempOrganization.setOrganizationDistrict(districtRepository.findById(organizationDTO.getOrganizationDistrictId()).orElse(null));
//        }
//
//        if (organizationDTO.getOrganizationCityId() != null){
//            tempOrganization.setOrganizationCity(cityRepository.findById(organizationDTO.getOrganizationCityId()).orElse(null));
//        }
        return tempCase;
    }

    /**
     * Updates an existing Case entity from an CaseDTO.
     *
     * @param legalCase         The existing Case entity
     * @param legalCaseDTO      The CaseDTO containing updated details
     * @return The updated Case entity
     */
    private LegalCase updateEntityFromDto(LegalCase legalCase, LegalCaseDTO legalCaseDTO) {
        legalCase.setLegalCaseTitle(legalCaseDTO.getLegalCaseTitle());
        legalCase.setLegalCaseDescription(legalCaseDTO.getLegalCaseDescription());
        legalCase.setLegalCaseStatus(legalCaseDTO.getLegalCaseStatus());

        legalCase.setLegalCaseDocument(legalCaseDTO.getLegalCaseDocument());
        legalCase.setLegalCaseDate(legalCaseDTO.getLegalCaseDate());


//        organization.setOrganizationEmail(organizationDTO.getOrganizationEmail());
//        organization.setOrganizationPhone(organizationDTO.getOrganizationPhone());
//        organization.setOrganizationStreet1(organizationDTO.getOrganizationStreet1());
//        organization.setOrganizationStreet2(organizationDTO.getOrganizationStreet2());
//        organization.setOrganizationStreetZipcode(organizationDTO.getOrganizationStreetZipcode());

//        if (organizationDTO.getOrganizationCountryId() != null){
//            organization.setOrganizationCountry(countryRepository.findById(organizationDTO.getOrganizationCountryId()).orElse(null));
//        }
//
//        if (organizationDTO.getOrganizationStateId() != null){
//            organization.setOrganizationState(stateRepository.findById(organizationDTO.getOrganizationStateId()).orElse(null));
//        }
//
//        if (organizationDTO.getOrganizationDistrictId() != null){
//            organization.setOrganizationDistrict(districtRepository.findById(organizationDTO.getOrganizationDistrictId()).orElse(null));
//        }
//
//        if (organizationDTO.getOrganizationCityId() != null){
//            organization.setOrganizationCity(cityRepository.findById(organizationDTO.getOrganizationCityId()).orElse(null));
//        }

        return legalCase;
    }
}
