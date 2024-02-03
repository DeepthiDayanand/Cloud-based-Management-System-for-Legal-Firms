package com.capstonelegal.organization.controller;

import com.capstonelegal.common.model.PageResponse;
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
@RequestMapping("/v1/organizations")
@Api(value = "Organization Controller", tags="04 - Organization Management", description = "Operations pertaining to organizations in Capstone Legal")
//@Tag(name = "Organization Management")
@Slf4j
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Fetches a paginated list of organizations with optional filtering.
     *
     * @param organizationName Optional organization name for filtering.
     * @param countryName      Optional country name for filtering.
     * @param stateName        Optional state name for filtering.
     * @param districtName     Optional district name for filtering.
     * @param cityName         Optional city name for filtering.
     * @param pageable         The Pageable object for pagination.
     * @return ResponseEntity containing a Page of OrganizationDTO objects.
     */
    @GetMapping
    @ApiOperation(value = "Get paginated list of organizations with optional filtering")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved organizations"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PageResponse<OrganizationDTO>> getOrganizations(
            @RequestParam(required = false) String organizationName,
            @RequestParam(required = false) String countryName,
            @RequestParam(required = false) String stateName,
            @RequestParam(required = false) String districtName,
            @RequestParam(required = false) String cityName,
            Pageable pageable) {
        log.info("Fetching organizations with filters - organizationName: {}, countryName: {}, stateName: {}, districtName: {}, cityName: {}",
                organizationName, countryName, stateName, districtName, cityName);

        Page<OrganizationDTO> organizations = organizationService.getOrganizationsByFilters(pageable, organizationName, countryName, stateName, districtName, cityName);
        PageResponse<OrganizationDTO> response = new PageResponse<>(organizations.getContent(), organizations.getTotalElements());
        log.info("Returning {} organization out of total {}", organizations.getNumberOfElements(), organizations.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Fetches details of a specific organization.
     *
     * @param organizationId The ID of the organization to fetch.
     * @return ResponseEntity containing the OrganizationDTO of the requested organization.
     * @throws ResourceNotFoundException If the organization is not found.
     */
    @GetMapping("/{organizationId}")
    @ApiOperation(value = "Get details of a specific organization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved organization details"),
            @ApiResponse(responseCode = "404", description = "Organization not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<OrganizationDTO> viewOrganization(@PathVariable String organizationId) {
        log.info("Fetching details of organization with ID: {}", organizationId);
        OrganizationDTO organizationDTO = organizationService.viewOrganization(organizationId);
        return ResponseEntity.ok(organizationDTO);
    }

    /**
     * Creates a new organization.
     *
     * @param organizationDTO The DTO containing organization details.
     * @return ResponseEntity containing the created OrganizationDTO.
     */
    @PostMapping
    @ApiOperation(value = "Create a new organization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Organization created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<OrganizationDTO> createOrganization(@RequestBody OrganizationDTO organizationDTO) {
        log.info("Creating a new organization");
        organizationDTO.setOrganizationId(UUIDUtil.generateUUID());
        OrganizationDTO createdOrganization = organizationService.createOrganization(organizationDTO);
        return ResponseEntity.ok(createdOrganization);
    }

    /**
     * Updates an existing organization.
     *
     * @param organizationId  The ID of the organization to update.
     * @param organizationDTO The DTO containing updated organization details.
     * @return ResponseEntity containing the updated OrganizationDTO.
     * @throws ResourceNotFoundException If the organization is not found.
     */
    @PutMapping("/{organizationId}")
    @ApiOperation(value = "Update an existing organization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Organization updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Organization not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable String organizationId,
                                                              @RequestBody OrganizationDTO organizationDTO) {
        log.info("Updating organization with ID: {}", organizationId);
        OrganizationDTO updatedOrganization = organizationService.updateOrganization(organizationId, organizationDTO);
        return ResponseEntity.ok(updatedOrganization);
    }

    /**
     * Deletes an organization.
     *
     * @param organizationId The ID of the organization to delete.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{organizationId}")
    @ApiOperation(value = "Delete an organization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Organization deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Organization not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteOrganization(@PathVariable String organizationId) {
        log.info("Deleting organization with ID: {}", organizationId);
        organizationService.deleteOrganization(organizationId);
        return ResponseEntity.noContent().build();
    }
}
