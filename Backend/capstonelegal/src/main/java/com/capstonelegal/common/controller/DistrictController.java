package com.capstonelegal.common.controller;

import com.capstonelegal.common.model.PageResponse;
import com.capstonelegal.common.model.dto.DistrictDTO;
import com.capstonelegal.common.model.entities.District;
import com.capstonelegal.common.service.DistrictService;
import com.capstonelegal.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller class to handle district related operations.
 */
@Api(value = "District Controller", tags = "03 - District Management", description = "Operations pertaining to districts in Capstone Legal")
//@Tag(name = "District Management")
@Slf4j
@RestController
@RequestMapping("/v1/districts")
public class DistrictController {

    private final DistrictService districtService;

    /**
     * Constructor to autowire DistrictService.
     * @param districtService Autowired DistrictService to handle service related operations.
     */
    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    /**
     * Fetches all districts with optional filtering.
     * @param countryName Optional country name to filter districts.
     * @param stateName Optional state name to filter districts.
     * @param districtName Optional district name to filter districts.
     * @param pageable Pageable object for pagination.
     * @return ResponseEntity containing the page response of districts.
     */
    @ApiOperation(value = "Get all districts with pagination and optional filtering")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved districts"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<PageResponse<DistrictDTO>> getAllDistricts(
            @ApiParam(value = "Country name to filter districts") @RequestParam(required = false) String countryName,
            @ApiParam(value = "State name to filter districts") @RequestParam(required = false) String stateName,
            @ApiParam(value = "District name to filter districts") @RequestParam(required = false) String districtName,
            Pageable pageable) {
        log.info("Fetching districts with filters - country name: {}, state name: {}, district name: {}, page: {}, size: {}", countryName, stateName, districtName, pageable.getPageNumber(), pageable.getPageSize());
        Page<DistrictDTO> page = districtService.getDistrictsByFilters(countryName, stateName, districtName, pageable);
        PageResponse<DistrictDTO> response = new PageResponse<>(page.getContent(), page.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Creates a new district for a given country and state IDs.
     * @param countryId ID of the country.
     * @param stateId ID of the state.
     * @param district District entity to be created.
     * @return ResponseEntity containing the details of created district.
     */
    @ApiOperation(value = "Create a new district for given country and state IDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "District successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/country/{countryId}/state/{stateId}")
    public ResponseEntity<DistrictDTO> createDistrict(@ApiParam(name = "countryId", type = "String", value = "ID of the country for the district", required = true) @PathVariable String countryId,
                                                      @ApiParam(name = "stateId", type = "String", value = "ID of the state for the district", required = true) @PathVariable String stateId,
                                                      @ApiParam(name = "district", type = "District", value = "District entity to be created", required = true) @RequestBody District district) {
        log.info("Creating District for country id: {} and state id: {}", countryId, stateId);
        district.setDistrictId(UUIDUtil.generateUUID());
        DistrictDTO createdDistrict = districtService.createDistrict(countryId, stateId, district);
        return new ResponseEntity<>(createdDistrict, HttpStatus.CREATED);
    }

    /**
     * Fetches a district by ID.
     * @param id ID of the district.
     * @return ResponseEntity containing the district details.
     */
    @ApiOperation(value = "Get district by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved district"),
            @ApiResponse(responseCode = "404", description = "District not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DistrictDTO> getDistrictById(
            @ApiParam(value = "ID of the district", required = true) @PathVariable String id) {
        log.info("Fetching district with id: {}", id);
        DistrictDTO district = districtService.getDistrictById(id);
        if (district == null) {
            log.error("District not found with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(district, HttpStatus.OK);
    }

    /**
     * Updates an existing district for a given country, state, and district IDs.
     * @param countryId ID of the country.
     * @param stateId ID of the state.
     * @param districtId ID of the district.
     * @param district District entity with updated details.
     * @return ResponseEntity containing the details of updated district.
     */
    @ApiOperation(value = "Update an existing district for given country, state, and district IDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated district"),
            @ApiResponse(responseCode = "404", description = "District not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/country/{countryId}/state/{stateId}/district/{districtId}")
    public ResponseEntity<DistrictDTO> updateDistrict(@ApiParam(name = "countryId", type = "String", value = "ID of the country for the district", required = true) @PathVariable String countryId,
                                                      @ApiParam(name = "stateId", type = "String", value = "ID of the state for the district", required = true) @PathVariable String stateId,
                                                      @ApiParam(name = "districtId", type = "String", value = "ID of the district to be updated", required = true) @PathVariable String districtId,
                                                      @ApiParam(name = "district", type = "District", value = "District entity with updated details", required = true) @RequestBody District district) {
        log.info("Updating district with id: {} for state id: {} and country id: {}", districtId, stateId, countryId);
        try {
            district.setDistrictId(districtId);
            DistrictDTO updatedDistrict = districtService.updateDistrict(countryId, stateId, district);
            return new ResponseEntity<>(updatedDistrict, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            log.error("District not found with id: {}", districtId, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes a district by ID.
     * @param id ID of the district to be deleted.
     * @return ResponseEntity with no content.
     */
    @ApiOperation(value = "Delete district by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted district"),
            @ApiResponse(responseCode = "404", description = "District not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrict(
            @ApiParam(value = "ID of the district to be deleted", required = true) @PathVariable String id) {
        log.info("Deleting district with id: {}", id);
        try {
            districtService.deleteDistrict(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException ex) {
            log.error("District not found with id: {}", id, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
