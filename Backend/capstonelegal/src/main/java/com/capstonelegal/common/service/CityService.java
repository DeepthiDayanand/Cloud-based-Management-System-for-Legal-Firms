package com.capstonelegal.common.service;

import com.capstonelegal.common.model.dto.CityDTO;
import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.model.entities.District;
import com.capstonelegal.common.model.entities.State;
import com.capstonelegal.common.repository.CityRepository;
import com.capstonelegal.common.repository.CountryRepository;
import com.capstonelegal.common.repository.DistrictRepository;
import com.capstonelegal.common.repository.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class to handle city related operations.
 */
@Slf4j
@Service
public class CityService {

    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    /**
     * Constructor to autowire CityRepository, DistrictRepository, StateRepository, and CountryRepository.
     * @param cityRepository Autowired CityRepository to handle city related operations.
     * @param districtRepository Autowired DistrictRepository to handle district related operations.
     * @param stateRepository Autowired StateRepository to handle state related operations.
     * @param countryRepository Autowired CountryRepository to handle country related operations.
     */
    @Autowired
    public CityService(CityRepository cityRepository, DistrictRepository districtRepository, StateRepository stateRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    /**
     * Fetches all cities with optional filtering.
     * @param countryName Optional country name to filter cities.
     * @param stateName Optional state name to filter cities.
     * @param districtName Optional district name to filter cities.
     * @param cityName Optional city name to filter cities.
     * @param pageable Pageable object for pagination.
     * @return Page of CityDTO.
     */
    public Page<CityDTO> getCitiesByFilters(Pageable pageable, String countryName, String stateName, String districtName, String cityName ) {
        log.info("Fetching cities with filters - country name: {}, state name: {}, district name: {}, city name: {}, page: {}, size: {}", countryName, stateName, districtName, cityName, pageable.getPageNumber(), pageable.getPageSize());

        // Use Specification to create custom query
        Page<City> cities = cityRepository.findAll((Specification<City>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtering by country name
            if (countryName != null && !countryName.isEmpty()) {
                Join<City, District> districtJoin = root.join("district");
                Join<District, State> stateJoin = districtJoin.join("state");
                Join<State, Country> countryJoin = stateJoin.join("country");
                predicates.add(criteriaBuilder.like(countryJoin.get("countryName"), "%" + countryName + "%"));
            }

            // Filtering by state name
            if (stateName != null && !stateName.isEmpty()) {
                Join<City, District> districtJoin = root.join("district");
                Join<District, State> stateJoin = districtJoin.join("state");
                predicates.add(criteriaBuilder.like(stateJoin.get("stateName"), "%" + stateName + "%"));
            }

            // Filtering by district name
            if (districtName != null && !districtName.isEmpty()) {
                Join<City, District> districtJoin = root.join("district");
                predicates.add(criteriaBuilder.like(districtJoin.get("districtName"), "%" + districtName + "%"));
            }

            // Filtering by city name
            if (cityName != null && !cityName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("cityName"), "%" + cityName + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return cities.map(CityDTO::fromCityEntity);
    }

    /**
     * Retrieves the city with the given id.
     * @param id The id of the desired city
     * @return The desired CityDTO object, or throws EntityNotFoundException if not found
     */
    public CityDTO getCityById(String id) {
        log.info("Request to get city by id: {}", id);
        City city = cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));
        return CityDTO.fromCityEntity(city);
    }

    /**
     * Creates a new city.
     * @param countryId The id of the country associated with the city
     * @param stateId The id of the state associated with the city
     * @param districtId The id of the district associated with the city
     * @param city The city to create
     * @return The created CityDTO object
     */
    public CityDTO createCity(String countryId, String stateId, String districtId, City city) {
        log.info("Creating a new city for Country ID: {}, State ID: {}, District ID: {}", countryId, stateId, districtId);
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id " + countryId));
        State state = stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException("State not found with id " + stateId));
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new EntityNotFoundException("District not found with id " + districtId));

        // Set country to state
        state.setCountry(country);

        // Set state to district
        district.setState(state);

        // Set district to city
        city.setDistrict(district);

        // Save new city
        City savedCity = cityRepository.save(city);

        return CityDTO.fromCityEntity(savedCity);
    }

    /**
     * Updates an existing city.
     * @param countryId The id of the country associated with the city
     * @param stateId The id of the state associated with the city
     * @param districtId The id of the district associated with the city
     * @param city The city to update
     * @return The updated CityDTO object
     */
    public CityDTO updateCity(String countryId, String stateId, String districtId, City city) {
        log.info("Updating a new city for Country ID: {}, State ID: {}, District ID: {}", countryId, stateId, districtId);
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id " + countryId));
        State state = stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotFoundException("State not found with id " + stateId));
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new EntityNotFoundException("District not found with id " + districtId));

        // Set country to state
        state.setCountry(country);

        // Set state to district
        district.setState(state);

        // Set district to city
        city.setDistrict(district);

        // Update city
        City savedCity = cityRepository.save(city);

        return CityDTO.fromCityEntity(savedCity);
    }

    /**
     * Deletes the city with the given id.
     * @param id The id of the city to delete
     */
    public void deleteCity(String id) {
        log.info("Request to delete city by id: {}", id);
        if (!cityRepository.existsById(id)) {
            throw new EntityNotFoundException("City not found with id: " + id);
        }
        cityRepository.deleteById(id);
    }
}
