package com.capstonelegal.common.controller;

import com.capstonelegal.common.model.PageResponse;
import com.capstonelegal.common.model.dto.CityDTO;
import com.capstonelegal.common.model.entities.City;
import com.capstonelegal.common.service.CityService;
import com.capstonelegal.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * CityController class to handle requests related to City entity.
 * This controller contains methods to create, read, update, and delete cities.
 */
@Api(value = "City Controller", tags = "04 - City Management", description = "Operations pertaining to cities in Capstone Legal")
//@Tag(name = "City Management")
@Slf4j
@RestController
@RequestMapping("/v1/cities")
public class CityController {

    private final CityService cityService;

    /**
     * Constructor to autowire CityService.
     * @param cityService Autowired CityService to handle city related operations.
     */
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Fetch all cities with optional filters.
     *
     * @param pageable Pageable object containing pagination parameters.
     * @param countryName Optional filter by country name.
     * @param stateName Optional filter by state name.
     * @param districtName Optional filter by district name.
     * @param cityName Optional filter by city name.
     * @return ResponseEntity containing a page of cities and HTTP status.
     */
    @ApiOperation(value = "Fetch all cities with optional filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved cities list"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<PageResponse<CityDTO>> getAllCities(Pageable pageable,
                                                              @ApiParam(value = "Optional filter by country name") @RequestParam(required = false) String countryName,
                                                              @ApiParam(value = "Optional filter by state name") @RequestParam(required = false) String stateName,
                                                              @ApiParam(value = "Optional filter by district name") @RequestParam(required = false) String districtName,
                                                              @ApiParam(value = "Optional filter by city name") @RequestParam(required = false) String cityName) {        log.info("Fetching cities with filters - country name: {}, state name: {}, district name: {}, city name: {}, page: {}, size: {}", countryName, stateName, districtName, cityName, pageable.getPageNumber(), pageable.getPageSize());

        Page<CityDTO> page = cityService.getCitiesByFilters(pageable, countryName, stateName, districtName, cityName);
        PageResponse<CityDTO> response = new PageResponse<>(page.getContent(), page.getTotalElements());
        log.info("Returning {} cities out of total {}", page.getNumberOfElements(), page.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Fetch a city by ID.
     *
     * @param id ID of the city to fetch.
     * @return ResponseEntity containing the fetched city and HTTP status.
     */
    @ApiOperation(value = "Fetch a city by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved city"),
            @ApiResponse(responseCode = "404", description = "City not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@ApiParam(value = "ID of the city to fetch", required = true) @PathVariable String id) {
        log.info("Fetching city with id: {}", id);

        CityDTO city = cityService.getCityById(id);
        if(city == null) {
            log.warn("No matching city records found for ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            log.info("Matching city record found for ID: {}", id);
            return new ResponseEntity<>(city, HttpStatus.OK);
        }
    }

    /**
     * Creates a new city.
     *
     * @param countryId ID of the country where the city is located.
     * @param stateId ID of the state where the city is located.
     * @param districtId ID of the district where the city is located.
     * @param city City object to create.
     * @return ResponseEntity containing the created city and HTTP status.
     */
    @ApiOperation(value = "Creates a new city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created city"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/country/{countryId}/state/{stateId}/district/{districtId}")
    public ResponseEntity<CityDTO> createCity(
            @ApiParam(value = "ID of the country where the city is located", required = true) @PathVariable String countryId,
            @ApiParam(value = "ID of the state where the city is located", required = true) @PathVariable String stateId,
            @ApiParam(value = "ID of the district where the city is located", required = true) @PathVariable String districtId,
            @ApiParam(value = "City object to create", required = true) @RequestBody City city
    ) {
        log.info("Creating a city with Name: {}", city.getCityName());
        city.setCityId(UUIDUtil.generateUUID());
        CityDTO createdCity = cityService.createCity(countryId, stateId, districtId, city);
        log.info("City with ID: {} created successfully", createdCity.getCityId());
        return new ResponseEntity<>(createdCity, HttpStatus.CREATED);
    }


    /**
     * Updates an existing city.
     *
     * @param countryId ID of the country where the city is located.
     * @param stateId ID of the state where the city is located.
     * @param districtId ID of the district where the city is located.
     * @param cityId ID of the city to update.
     * @param city Updated City object.
     * @return ResponseEntity containing the updated city and HTTP status.
     */
    @ApiOperation(value = "Updates an existing city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated city"),
            @ApiResponse(responseCode = "404", description = "City not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/country/{countryId}/state/{stateId}/district/{districtId}/city/{cityId}")
    public ResponseEntity<CityDTO> updateCity(
            @ApiParam(value = "ID of the country where the city is located", required = true) @PathVariable String countryId,
            @ApiParam(value = "ID of the state where the city is located", required = true) @PathVariable String stateId,
            @ApiParam(value = "ID of the district where the city is located", required = true) @PathVariable String districtId,
            @ApiParam(value = "ID of the city to update", required = true) @PathVariable String cityId,
            @ApiParam(value = "Updated City object", required = true) @RequestBody City city
    ) {
        log.info("Updating city with ID: {}, Name: {}", cityId, city.getCityName());

        CityDTO existingCity = cityService.getCityById(cityId);
        if(existingCity == null) {
            log.warn("No Matching city found to update with ID: {}", cityId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        city.setCityId(cityId);
        CityDTO updatedCity = cityService.updateCity(countryId, stateId, districtId, city);
        log.info("City with ID: {} updated successfully", updatedCity.getCityId());
        return new ResponseEntity<>(updatedCity, HttpStatus.OK);
    }

    /**
     * Deletes an existing city.
     *
     * @param id ID of the city to delete.
     * @return ResponseEntity containing HTTP status.
     */
    @ApiOperation(value = "Deletes an existing city", notes = "This API deletes an existing city based on the ID provided.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted city"),
            @ApiResponse(responseCode = "404", description = "City not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@ApiParam(value = "ID of the city to delete", required = true) @PathVariable String id) {
        log.info("Deleting city with ID: {}", id);

        CityDTO existingCity = cityService.getCityById(id);
        if(existingCity == null) {
            log.warn("No Matching city found to delete with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        cityService.deleteCity(id);
        log.info("City with ID: {} deleted successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
