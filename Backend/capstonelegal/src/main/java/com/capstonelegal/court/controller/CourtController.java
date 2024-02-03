package com.capstonelegal.court.controller;

import com.capstonelegal.court.model.entities.Court;
import com.capstonelegal.court.service.CourtService;
import com.capstonelegal.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * REST controller for managing {@link Court}.
 */
@RestController
@RequestMapping("/v1/courts")
@Slf4j
@Api(value = "Court Controller", tags = "07 - Court Management",description = "Operations pertaining to courts in Capstone Legal")
public class CourtController {

    private final CourtService courtService;

    @Autowired
    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }
    /**
     * Retrieve all existing courts.
     *
     * @return list of all available courts.
     */
    @GetMapping
    @ApiOperation(value = "Retrieve all courts", response = List.class)
    public ResponseEntity<List<Court>> getAllCourts() {
        log.info("Fetching all courts");
        List<Court> courts = courtService.getAllCourts();
        return new ResponseEntity<>(courts, HttpStatus.OK);
    }

    /**
     * Fetches a specific court by its ID.
     *
     * @param id The ID of the court.
     * @return The court if found, otherwise a 404 status.
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Find Court by ID", response = Court.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved court"),
            @ApiResponse(responseCode = "404", description = "Court not found")
    })
    public ResponseEntity<Court> getCourtById(@ApiParam(value = "ID of the court to retrieve", required = true) @PathVariable String id) {
        log.info("Fetching court with ID: {}", id);
        Court court = courtService.getCourtById(id);
        if (court == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(court, HttpStatus.OK);
    }

    /**
     * Creates a new court entry.
     *
     * @param court The details of the court.
     * @return The created court details.
     */
    @PostMapping
    @ApiOperation(value = "Create a new Court", response = Court.class)
    @ApiResponse(responseCode = "201", description = "Successfully created court")
    public ResponseEntity<Court> createCourt(@RequestBody Court court) {
        log.info("Request to create a new court: {}", court);
        court.setCourtId(UUIDUtil.generateUUID());
        Court createdOrUpdatedCourt = courtService.createOrUpdateCourt(court);
        return new ResponseEntity<>(createdOrUpdatedCourt, HttpStatus.CREATED);
    }

    /**
     * Updates an existing court by its ID.
     *
     * @param id The ID of the court to update.
     * @param court The updated details of the court.
     * @return The updated court details.
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "Update a Court by its ID", response = Court.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated court"),
            @ApiResponse(responseCode = "404", description = "Court not found")
    })
    public ResponseEntity<Court> updateCourt(@ApiParam(value = "ID of the court to update", required = true) @PathVariable String id, @RequestBody Court court) {
        log.info("Request to update court with ID {}: {}", id, court);
        Court existingCourt = courtService.getCourtById(id);
        if (existingCourt == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Court createdOrUpdatedCourt = courtService.createOrUpdateCourt(court);
        return new ResponseEntity<>(createdOrUpdatedCourt, HttpStatus.OK);
    }

    /**
     * Deletes an existing court by its ID.
     *
     * @param id The ID of the court to delete.
     * @return Status code indicating the result of the deletion operation.
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a Court by its ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted court")
    public ResponseEntity<Void> deleteCourt(@ApiParam(value = "ID of the court to delete", required = true) @PathVariable String id) {
        log.info("Request to delete court with ID: {}", id);
        Court court = courtService.getCourtById(id);
        if (court == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courtService.deleteCourt(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
