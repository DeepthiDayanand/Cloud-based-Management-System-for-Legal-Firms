package com.capstonelegal.judge.service;

import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.model.entities.District;
import com.capstonelegal.common.model.entities.State;
import com.capstonelegal.common.repository.CityRepository;
import com.capstonelegal.common.repository.CountryRepository;
import com.capstonelegal.common.repository.DistrictRepository;
import com.capstonelegal.common.repository.StateRepository;
import com.capstonelegal.court.model.entities.Court;
import com.capstonelegal.court.repository.CourtRepository;
import com.capstonelegal.judge.model.DTO.JudgeDTO;
import com.capstonelegal.judge.model.entities.*;
import com.capstonelegal.judge.repository.*;
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
 * Service class for managing operations related to Judge entities.
 * Handles tasks such as creating, updating, deleting and retrieving Judges with optional filtering.
 */
@Slf4j
@Service
public class JudgeService {
    private final JudgeRepository judgeRepository;
    private final CourtRepository courtRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    /**
     * Constructor to autowire the necessary repositories.
     * @param judgeRepository   Autowired JudgeRepository to handle judge related operations.
     * @param courtRepository   Autowired CourtRepository to handle court related operations.
     * @param cityRepository    Autowired CityRepository to handle city related operations.
     * @param districtRepository Autowired DistrictRepository to handle district related operations.
     * @param stateRepository   Autowired StateRepository to handle state related operations.
     * @param countryRepository Autowired CountryRepository to handle country related operations.
     */
    @Autowired
    public JudgeService(JudgeRepository judgeRepository, CourtRepository courtRepository,
                        CityRepository cityRepository, DistrictRepository districtRepository,
                        StateRepository stateRepository, CountryRepository countryRepository) {
        this.judgeRepository = judgeRepository;
        this.courtRepository = courtRepository;
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    /**
     * Fetches all judges with optional filtering by city, district, state, country, court, and case names.
     * @param pageable Pageable object for pagination.
     * @param cityName Optional city name to filter judges.
     * @param districtName Optional district name to filter judges.
     * @param stateName Optional state name to filter judges.
     * @param countryName Optional country name to filter judges.
     * @param courtName Optional court name to filter judges.
     * @param caseName Optional case name to filter judges.
     * @return Page of JudgeDTO.
     */
    public Page<JudgeDTO> getJudgesByFilters(Pageable pageable, String cityName, String districtName, String stateName,
                                             String countryName, String courtName, String caseName) {
        log.info("Fetching judges with filters - city name: {}, district name: {}, state name: {}, country name: {}, " +
                        "court name: {}, case name: {}, page: {}, size: {}", cityName, districtName, stateName, countryName,
                courtName, caseName, pageable.getPageNumber(), pageable.getPageSize());

        Page<Judge> judges = judgeRepository.findAll((Specification<Judge>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtering by city name
            if (cityName != null && !cityName.isEmpty()) {
                Join<Judge, Court> courtJoin = root.join("court");
                Join<Court, City> cityJoin = courtJoin.join("city");
                predicates.add(criteriaBuilder.like(cityJoin.get("cityName"), "%" + cityName + "%"));
            }

            // Filtering by district name
            if (districtName != null && !districtName.isEmpty()) {
                Join<Judge, Court> courtJoin = root.join("court");
                Join<Court, City> cityJoin = courtJoin.join("city");
                Join<City, District> districtJoin = cityJoin.join("district");
                predicates.add(criteriaBuilder.like(districtJoin.get("districtName"), "%" + districtName + "%"));
            }

            // Filtering by state name
            if (stateName != null && !stateName.isEmpty()) {
                Join<Judge, Court> courtJoin = root.join("court");
                Join<Court, City> cityJoin = courtJoin.join("city");
                Join<City, District> districtJoin = cityJoin.join("district");
                Join<District, State> stateJoin = districtJoin.join("state");
                predicates.add(criteriaBuilder.like(stateJoin.get("stateName"), "%" + stateName + "%"));
            }

            // Filtering by country name
            if (countryName != null && !countryName.isEmpty()) {
                Join<Judge, Court> courtJoin = root.join("court");
                Join<Court, City> cityJoin = courtJoin.join("city");
                Join<City, District> districtJoin = cityJoin.join("district");
                Join<District, State> stateJoin = districtJoin.join("state");
                Join<State, Country> countryJoin = stateJoin.join("country");
                predicates.add(criteriaBuilder.like(countryJoin.get("countryName"), "%" + countryName + "%"));
            }

            // Filtering by court name
            if (courtName != null && !courtName.isEmpty()) {
                Join<Judge, Court> courtJoin = root.join("court");
                predicates.add(criteriaBuilder.like(courtJoin.get("courtName"), "%" + courtName + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        return judges.map(JudgeDTO::fromJudgeEntity);
    }
    public JudgeDTO getJudgeById(String id) {
        log.info("Request to get judge by id: {}", id);
        Judge judge = judgeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Judge not found with id: " + id));
        return JudgeDTO.fromJudgeEntity(judge);
    }

    public JudgeDTO createJudge(String courtId, Judge judge) {
        log.info("Creating a new judge for Court ID: {}", courtId);
        Court court = courtRepository.findById(courtId)
                .orElseThrow(() -> new EntityNotFoundException("Court not found with id " + courtId));

        judge.setCourt(court);
        Judge savedJudge = judgeRepository.save(judge);

        return JudgeDTO.fromJudgeEntity(savedJudge);
    }

    public JudgeDTO updateJudge(String courtId, Judge judge) {
        log.info("Updating a judge for Court ID: {}", courtId);
        Court court = courtRepository.findById(courtId)
                .orElseThrow(() -> new EntityNotFoundException("Court not found with id " + courtId));
        judge.setCourt(court);
        Judge savedJudge = judgeRepository.save(judge);
        return JudgeDTO.fromJudgeEntity(savedJudge);
    }

    public void deleteJudge(String id) {
        log.info("Request to delete judge by id: {}", id);
        if (!judgeRepository.existsById(id)) {
            throw new EntityNotFoundException("Judge not found with id: " + id);
        }
        judgeRepository.deleteById(id);
    }
}

