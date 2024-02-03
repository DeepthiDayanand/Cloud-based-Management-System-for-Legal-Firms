package com.capstonelegal.common.service;

import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.model.entities.District;
import com.capstonelegal.common.model.dto.DistrictDTO;
import com.capstonelegal.common.model.entities.State;
import com.capstonelegal.common.repository.CountryRepository;
import com.capstonelegal.common.repository.DistrictRepository;
import com.capstonelegal.common.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;

/**
 * Service class to handle district related operations.
 */
@Slf4j
@Service
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    /**
     * Constructor to autowire DistrictRepository, StateRepository, and CountryRepository.
     * @param districtRepository Autowired DistrictRepository to handle district related operations.
     * @param stateRepository Autowired StateRepository to handle state related operations.
     * @param countryRepository Autowired CountryRepository to handle country related operations.
     */
    @Autowired
    public DistrictService(DistrictRepository districtRepository, StateRepository stateRepository, CountryRepository countryRepository) {
        this.districtRepository = districtRepository;
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    /**
     * Fetches all districts with optional filtering.
     * @param countryName Optional country name to filter districts.
     * @param stateName Optional state name to filter districts.
     * @param districtName Optional district name to filter districts.
     * @param pageable Pageable object for pagination.
     * @return Page of DistrictDTO.
     */
    public Page<DistrictDTO> getDistrictsByFilters(String countryName, String stateName, String districtName, Pageable pageable) {
        log.info("Fetching districts with filters - country name: {}, state name: {}, district name: {}, page: {}, size: {}", countryName, stateName, districtName, pageable.getPageNumber(), pageable.getPageSize());

        Page<District> districts = districtRepository.findAll((Specification<District>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (countryName != null && !countryName.isEmpty()) {
                Join<District, State> stateJoin = root.join("state");
                Join<State, Country> countryJoin = stateJoin.join("country");
                predicates.add(criteriaBuilder.like(countryJoin.get("countryName"), "%" + countryName + "%"));
            }

            if (stateName != null && !stateName.isEmpty()) {
                Join<District, State> stateJoin = root.join("state");
                predicates.add(criteriaBuilder.like(stateJoin.get("stateName"), "%" + stateName + "%"));
            }

            if (districtName != null && !districtName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("districtName"), "%" + districtName + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return districts.map(DistrictDTO::fromDistrictEntity);
    }

    /**
     * Retrieves the district with the given id.
     * @param id The id of the desired district
     * @return The desired DistrictDTO object, or null if not found
     */
    public DistrictDTO getDistrictById(String id) {
        log.info("Request to get district by id: {}", id);
        District district = districtRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("District not found with id: " + id));
        return DistrictDTO.fromDistrictEntity(district);
    }

    /**
     * Creates a new district.
     * @param countryId The id of the country associated with the district
     * @param stateId The id of the state associated with the district
     * @param district The district to create
     * @return The created DistrictDTO object
     */
    public DistrictDTO createDistrict(String countryId, String stateId, District district) {
        log.info("Creating a new district for Country ID: {} and State ID: {}", countryId, stateId);
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id " + countryId));
        State state = stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException("State not found with id " + stateId));
        state.setCountry(country);
        district.setState(state);
        District savedDistrict = districtRepository.save(district);
        return DistrictDTO.fromDistrictEntity(savedDistrict);
    }

    /**
     * Updates an existing district.
     * @param countryId The id of the country associated with the district
     * @param stateId The id of the state associated with the district
     * @param district The district to update
     * @return The updated DistrictDTO object
     */
    public DistrictDTO updateDistrict(String countryId, String stateId, District district) {
        log.info("Updating a new district for Country ID: {} and State ID: {}", countryId, stateId);
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id " + countryId));
        State state = stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException("State not found with id " + stateId));
        state.setCountry(country);
        district.setState(state);
        District savedDistrict = districtRepository.save(district);
        return DistrictDTO.fromDistrictEntity(savedDistrict);
    }

    /**
     * Deletes the district with the given id.
     * @param id The id of the district to delete
     */
    public void deleteDistrict(String id) {
        log.info("Request to delete district by id: {}", id);
        if (!districtRepository.existsById(id)) {
            throw new EntityNotFoundException("District not found with id: " + id);
        }
        districtRepository.deleteById(id);
    }
}
