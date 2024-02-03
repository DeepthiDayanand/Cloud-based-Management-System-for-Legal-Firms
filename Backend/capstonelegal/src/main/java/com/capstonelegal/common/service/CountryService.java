package com.capstonelegal.common.service;

import com.capstonelegal.common.model.dto.CountryDTO;
import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for executing business logic on {@link Country} entities.
 * Handles CRUD operations and retrieval using filters.
 */
@Service
@Slf4j
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /**
     * Retrieves all countries in a paginated format.
     *
     * @param pageable Pageable object with pagination information.
     * @return Page containing {@link CountryDTO} objects.
     */
    public Page<CountryDTO> getAllCountries(Pageable pageable) {
        log.info("Fetching all countries - page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<Country> countries = countryRepository.findAll(pageable);
        return countries.map(CountryDTO::fromCountryEntity);
    }

    /**
     * Retrieves a country by its ID and maps it to {@link CountryDTO}.
     *
     * @param id ID of the country.
     * @return {@link CountryDTO} representation of the country, or null if no country with the given ID exists.
     */
    public CountryDTO getCountryById(String id) {
        log.info("Fetching country by ID: {}", id);
        CountryDTO countryDTO= CountryDTO.fromCountryEntity(countryRepository.findById(id).orElse(null));
        return countryDTO;
    }

    /**
     * Creates or updates a country in the database and returns a {@link CountryDTO} representation of the entity.
     *
     * @param country Country entity to create or update.
     * @return {@link CountryDTO} representation of the created or updated country.
     */
    public CountryDTO createOrUpdateCountry(Country country) {
        log.info("Creating or updating country with ID: {}", country.getCountryId());
        CountryDTO countryDTO = CountryDTO.fromCountryEntity(countryRepository.save(country));
        return countryDTO;
    }

    /**
     * Deletes a country identified by the provided ID from the database.
     *
     * @param id ID of the country to delete.
     */
    public void deleteCountry(String id) {
        log.info("Deleting country with ID: {}", id);
        countryRepository.deleteById(id);
    }

    /**
     * Retrieves countries that match the given filter in a paginated format.
     *
     * @param countryName Country name to filter by.
     * @param pageable Pageable object with pagination information.
     * @return Page containing {@link CountryDTO} objects that match the filter.
     */
    @Transactional(readOnly = true)
    public Page<CountryDTO> findByFilters(String countryName, Pageable pageable) {
        log.info("Fetching countries by name filter: {}, page: {}, size: {}", countryName, pageable.getPageNumber(), pageable.getPageSize());

        Page<Country> countries = countryRepository.findAll((Specification<Country>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (countryName != null) {
                predicates.add(criteriaBuilder.like(root.get("countryName"), countryName + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return countries.map(CountryDTO::fromCountryEntity);
    }
}
