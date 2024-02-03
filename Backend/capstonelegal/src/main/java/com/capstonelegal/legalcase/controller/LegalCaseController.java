package com.capstonelegal.legalcase.controller;

import com.capstonelegal.common.model.PageResponse;
import com.capstonelegal.legalcase.model.dto.LegalCaseDTO;
import com.capstonelegal.legalcase.service.LegalCaseService;
import com.capstonelegal.organization.model.dto.OrganizationDTO;
import com.capstonelegal.organization.service.OrganizationService;
import com.capstonelegal.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capstonelegal.common.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/legalcases")
@Api(value = "Legal Case Controller", tags="06 - Legal Case Management", description = "Details pertaining to legal cases in Capstone Legal Project")
//@Tag(name = "Case Management")
@Slf4j
public class LegalCaseController {

    private final LegalCaseService legalCaseService;

    @Autowired
    public LegalCaseController(LegalCaseService legalCaseService) {
        this.legalCaseService = legalCaseService;
    }

    /**
     * Fetches a paginated list of organizations with optional filtering.
     *
     * @param legalCaseTitle Optional organization name for filtering.
//     * @param countryName      Optional country name for filtering.
//     * @param stateName        Optional state name for filtering.
//     * @param districtName     Optional district name for filtering.
//     * @param cityName         Optional city name for filtering.
     * @param pageable         The Pageable object for pagination.
     * @return ResponseEntity containing a Page of LegalCaseDTO objects.
     */
    @GetMapping
    @ApiOperation(value = "Get paginated list of legal cases with optional filtering")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved cases"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PageResponse<LegalCaseDTO>> getLegalCases(
            @RequestParam(required = false) String legalCaseTitle,
//            @RequestParam(required = false) String countryName,
//            @RequestParam(required = false) String stateName,
//            @RequestParam(required = false) String districtName,
//            @RequestParam(required = false) String cityName,
            Pageable pageable) {
        log.info("Fetching cases with filters - legalCaseTitle: {}",
                legalCaseTitle);

        Page<LegalCaseDTO> legalCases = legalCaseService.getLegalCasesByFilters(pageable, legalCaseTitle);
        PageResponse<LegalCaseDTO> response = new PageResponse<>(legalCases.getContent(), legalCases.getTotalElements());
        log.info("Returning legal cases", legalCases.getNumberOfElements(), legalCases.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Fetches details of a specific case.
     *
     * @param legalCaseId The ID of the legal case to fetch.
     * @return ResponseEntity containing the LegalCaseDTO of the requested case.
     * @throws ResourceNotFoundException If the case is not found.
     */
    @GetMapping("/{legalCaseId}")
    @ApiOperation(value = "Get details of a specific legal case")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved case details"),
            @ApiResponse(responseCode = "404", description = "Case not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<LegalCaseDTO> viewLegalCase(@PathVariable String legalCaseId) {
        log.info("Fetching details of a case with ID: {}", legalCaseId);
        LegalCaseDTO legalCaseDTO = legalCaseService.viewLegalCase(legalCaseId);
        return ResponseEntity.ok(legalCaseDTO);
    }

    /**
     * Creates a new organization.
     *
     * @param legalCaseDTO The DTO containing case details.
     * @return ResponseEntity containing the created legalCaseDTO.
     */
    @PostMapping
    @ApiOperation(value = "Create a new organization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Case created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<LegalCaseDTO> createLegalCase(@RequestBody LegalCaseDTO legalCaseDTO) {
        log.info("Creating a new case");
        legalCaseDTO.setLegalCaseId(UUIDUtil.generateUUID());
        LegalCaseDTO createdLegalCase = legalCaseService.createLegalCase(legalCaseDTO);
        return ResponseEntity.ok(createdLegalCase);
    }

    /**
     * Updates an existing case.
     *
     * @param legalCaseId  The ID of the case to update.
     * @param legalCaseDTO The DTO containing updated case details.
     * @return ResponseEntity containing the updated LegalCaseDTO.
     * @throws ResourceNotFoundException If the case is not found.
     */
    @PutMapping("/{legalCaseId}")
    @ApiOperation(value = "Update an existing case")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Case updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Case not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<LegalCaseDTO> updateLegalCase(@PathVariable String legalCaseId,
                                                              @RequestBody LegalCaseDTO legalCaseDTO) {
        log.info("Updating case with ID: {}", legalCaseId);
        LegalCaseDTO updatedLegalCase = legalCaseService.updateLegalCase(legalCaseId, legalCaseDTO);
        return ResponseEntity.ok(updatedLegalCase);
    }

    /**
     * Deletes a case.
     *
     * @param legalCaseId The ID of the case to delete.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{legalCaseId}")
    @ApiOperation(value = "Delete a case")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Case deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Case not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteLegalCase(@PathVariable String legalCaseId) {
        log.info("Deleting case with ID: {}", legalCaseId);
        legalCaseService.deleteLegalCase(legalCaseId);
        return ResponseEntity.noContent().build();
    }



}


