package com.capstonelegal.common.controller;

import com.capstonelegal.common.model.PageResponse;
import com.capstonelegal.common.model.dto.CountryDTO;
import com.capstonelegal.common.model.entities.Country;
import com.capstonelegal.common.service.CountryService;
import com.capstonelegal.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST Controller for managing countries.
 * This class provides endpoints for performing CRUD operations on countries.
 * Each endpoint provides detailed logging for tracking requests and possible errors.
 *
 */
@Api(value = "Country Controller", tags = "01 - Country Management", description = "Operations pertaining to countries in Capstone Legal")
@Slf4j
@RestController
@RequestMapping("/v1/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Fetches all countries with pagination.
     *
     * @param pageable Pagination information
     * @return ResponseEntity with list of countries
     */
    @ApiOperation(value = "View a list of available countries", response = Page.class)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of countries"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<PageResponse<CountryDTO>> getAllCountries(Pageable pageable) {
        log.info("Request received to fetch all countries - page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<CountryDTO> countries = countryService.getAllCountries(pageable);
        PageResponse<CountryDTO> response = new PageResponse<>(countries.getContent(), countries.getTotalElements());
        log.info("Responding with {} countries out of total {} countries", countries.getNumberOfElements(), countries.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Fetches a country by its ID.
     *
     * @param id ID of the country
     * @return ResponseEntity with country data
     */
    @ApiOperation(value = "Get a country by its ID", response = CountryDTO.class)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the country"),
            @ApiResponse(responseCode = "404", description = "Country with specified ID not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@ApiParam(value = "ID value for the country to be retrieved", required = true) @PathVariable String id) {
        log.info("Request received to fetch country with ID: {}", id);
        try {
            CountryDTO country = countryService.getCountryById(id);
            return ResponseEntity.ok(country);
        } catch (NoSuchElementException e) {
            log.error("Failed to fetch country with id: {} - Country not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Fetches countries by their name.
     *
     * @param countryName Name of the country
     * @param pageable Pagination information
     * @return ResponseEntity with list of countries
     */
    @ApiOperation(value = "Get a country by its name", response = List.class)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the countries"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/countryName/{countryName}")
    public ResponseEntity<List<CountryDTO>> getCountryByName(@ApiParam(value = "Name of the country to be retrieved", required = true) @PathVariable String countryName, Pageable pageable) {
        log.info("Request received to fetch countries by name: {}, page: {}, size: {}", countryName, pageable.getPageNumber(), pageable.getPageSize());
        Page<CountryDTO> countries = countryService.findByFilters(countryName, pageable);
        return ResponseEntity.ok(countries.getContent());
    }

    /**
     * Creates a new country.
     *
     * @param country Data of the country to be created
     * @return ResponseEntity with created country data
     */
    @ApiOperation(value = "Create a country", response = CountryDTO.class)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully created the country"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<CountryDTO> createCountry(@RequestBody Country country) {
        log.info("Request received to create new country with name: {}", country.getCountryName());
        country.setCountryId(UUIDUtil.generateUUID());
        CountryDTO createdCountry = countryService.createOrUpdateCountry(country);
        return new ResponseEntity<>(createdCountry, HttpStatus.CREATED);
    }

    /**
     * Updates a country.
     *
     * @param id ID of the country to be updated
     * @param country New data for the country
     * @return ResponseEntity with updated country data
     */
    @ApiOperation(value = "Update a country", response = CountryDTO.class)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated the country"),
            @ApiResponse(responseCode = "404", description = "Country with specified ID not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> updateCountry(@ApiParam(value = "ID value for the country to be updated", required = true) @PathVariable String id, @RequestBody Country country) {
        log.info("Request received to update country with ID: {}", id);
        try {
            country.setCountryId(id);
            CountryDTO updatedCountry = countryService.createOrUpdateCountry(country);
            return ResponseEntity.ok(updatedCountry);
        } catch (NoSuchElementException e) {
            log.error("Failed to update country with id: {} - Country not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a country by its ID.
     *
     * @param id ID of the country to be deleted
     * @return ResponseEntity with no content
     */
    @ApiOperation(value = "Delete a country")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successfully deleted the country"),
            @ApiResponse(responseCode = "404", description = "Country with specified ID not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@ApiParam(value = "ID value for the country to be deleted", required = true) @PathVariable String id) {
        log.info("Request received to delete country with ID: {}", id);
        try {
            countryService.deleteCountry(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            log.error("Failed to delete country with id: {} - Country not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
