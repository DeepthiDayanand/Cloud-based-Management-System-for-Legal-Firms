package com.capstonelegal.common.service;

import com.capstonelegal.common.model.dto.StateDTO;
import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.model.entities.State;
import com.capstonelegal.common.repository.CountryRepository;
import com.capstonelegal.common.repository.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * StateService class is responsible for handling all operations related to the State entity.
 */
@Slf4j
@Service
public class StateService {

    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public StateService(StateRepository stateRepository, CountryRepository countryRepository) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    /**
     * Fetch all State entities from the database with pagination and optional filtering by country name and state name.
     *
     * @param countryName Filter by country name. If null, this filter is ignored.
     * @param stateName   Filter by state name. If null, this filter is ignored.
     * @param pageable    Pagination information.
     * @return Page of StateDTO entities.
     */
    @Transactional(readOnly = true)
    public Page<StateDTO> getStates(String countryName, String stateName, Pageable pageable) {
        log.info("Fetching paginated and optionally filtered states from the database");

        Page<State> states = stateRepository.findAll((Specification<State>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (countryName != null && !countryName.isEmpty()) {
                Join<Object, Object> join = root.join("country");
                predicates.add(criteriaBuilder.like(join.get("countryName"), "%" + countryName + "%"));
            }

            if (stateName != null && !stateName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("stateName"), "%" + stateName + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return states.map(StateDTO::fromStateEntity);
    }

    /**
     * Fetch a State entity by its ID.
     *
     * @param id State entity ID.
     * @return StateDTO entity.
     * @throws NoSuchElementException if no entity with the given ID is found.
     */
    public StateDTO getStateById(String id) {
        log.info("Fetching state with ID {}", id);
        State state = stateRepository.findById(id).orElseThrow(() -> new NoSuchElementException("State not found with id " + id));
        return StateDTO.fromStateEntity(state);
    }

    /**
     * Create a new State entity.
     *
     * @param countryId ID of the country to which the state belongs.
     * @param state     State entity to create.
     * @return Created StateDTO entity.
     * @throws NoSuchElementException if no country with the given ID is found.
     */
    public StateDTO createState(String countryId, State state) {
        log.info("Creating a new state for country ID {}", countryId);
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NoSuchElementException("Country not found with id " + countryId));
        state.setCountry(country);
        return StateDTO.fromStateEntity(stateRepository.save(state));
    }

    /**
     * Update a State entity.
     *
     * @param countryId ID of the country to which the state belongs.
     * @param state     State entity to update.
     * @return Updated StateDTO entity.
     * @throws NoSuchElementException if no country with the given ID is found.
     */
    public StateDTO updateState(String countryId, State state) {
        log.info("Updating state {} for country ID {}", state.getStateId(), countryId);
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new NoSuchElementException("Country not found with id " + countryId));
        state.setCountry(country);
        return StateDTO.fromStateEntity(stateRepository.save(state));
    }

    /**
     * Delete a State entity.
     *
     * @param id ID of the State entity to delete.
     * @throws NoSuchElementException if no entity with the given ID is found.
     */
    public void deleteState(String id) {
        log.info("Deleting state with ID {}", id);
        if (!stateRepository.existsById(id)) {
            throw new NoSuchElementException("State not found with id " + id);
        }
        stateRepository.deleteById(id);
    }
}
