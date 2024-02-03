package com.capstonelegal.court.controller;

import com.capstonelegal.court.model.entities.CourtType;
import com.capstonelegal.court.service.CourtTypeService;
import com.capstonelegal.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

/**
 * REST controller for managing {@link CourtType}.
 */
@RestController
@RequestMapping("/v1/courtTypes")
@Slf4j
@Api(value = "CourtType Controller", tags = "06 - Court Type Management", description = "Operations pertaining to court types in Capstone Legal")
public class CourtTypeController {

    private final CourtTypeService courtTypeService;

    @Autowired
    public CourtTypeController(CourtTypeService courtTypeService) {
        this.courtTypeService = courtTypeService;
    }

    /**
     * Fetches and returns all court types.
     *
     * @return A list of all court types.
     */
    @ApiOperation(value = "View a list of available court types", response = List.class)
    @GetMapping
    public ResponseEntity<List<CourtType>> getAllCourtTypes() {
        log.info("Fetching all court types");
        List<CourtType> courtTypes = courtTypeService.getAllCourtTypes();
        return new ResponseEntity<>(courtTypes, HttpStatus.OK);
    }

    /**
     * Fetches and returns the court type with the specified ID.
     *
     * @param id ID of the court type to retrieve.
     * @return The court type with the specified ID, or 404 status if not found.
     */
    @ApiOperation(value = "Get a court type by its ID", response = CourtType.class)
    @GetMapping("/{id}")
    public ResponseEntity<CourtType> getCourtTypeById(@ApiParam(value = "CourtType ID to fetch", required = true) @PathVariable String id) {
        log.info("Fetching court type with ID: {}", id);
        CourtType courtType = courtTypeService.getCourtTypeById(id);
        if (courtType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courtType, HttpStatus.OK);
    }

    /**
     * Creates or updates a court type.
     *
     * @param courtType The court type information to create or update.
     * @return The created or updated court type.
     */
    @ApiOperation(value = "Create a new court type", response = CourtType.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created court type"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<CourtType> createCourtType(@RequestBody CourtType courtType) {
        log.info("Creating or updating court type: {}", courtType);
        courtType.setCourtTypeId(UUIDUtil.generateUUID());
        CourtType createdOrUpdatedCourtType = courtTypeService.createOrUpdateCourtType(courtType);
        return new ResponseEntity<>(createdOrUpdatedCourtType, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an existing court type", response = CourtType.class)
    @PutMapping("/{id}")
    public ResponseEntity<CourtType> updateCourtType(@ApiParam(value = "CourtType ID to update", required = true) @PathVariable String id, @RequestBody CourtType courtType) {
        log.info("Updating court type with ID: {}", id);
        CourtType existingCourtType = courtTypeService.getCourtTypeById(id);
        if(existingCourtType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CourtType createdOrUpdatedCourtType = courtTypeService.createOrUpdateCourtType(courtType);
        return new ResponseEntity<>(createdOrUpdatedCourtType, HttpStatus.OK);
    }

    /**
     * Deletes the court type with the specified ID.
     *
     * @param id ID of the court type to delete.
     * @return Response with HTTP status code.
     */
    @ApiOperation(value = "Delete a court type by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourtType(@ApiParam(value = "CourtType ID to delete", required = true) @PathVariable String id) {
        log.info("Deleting court type with ID: {}", id);
        CourtType courtType = courtTypeService.getCourtTypeById(id);
        if (courtType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courtTypeService.deleteCourtType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
