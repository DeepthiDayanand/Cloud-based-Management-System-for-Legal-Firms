package com.capstonelegal.common.controller;

import com.capstonelegal.common.model.PageResponse;
import com.capstonelegal.common.model.entities.State;
import com.capstonelegal.common.model.dto.StateDTO;
import com.capstonelegal.common.service.StateService;
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
 * Controller for handling operations related to States.
 */
@Api(value = "State Controller", tags = "02 - State Management", description = "Operations pertaining to states in Capstone Legal")
//@Tag(name = "State Management")
@Slf4j
@RestController
@RequestMapping("/v1/states")
public class StateController {

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    /**
     * Get a list of states optionally filtered by country or state name
     *
     * @param countryName Country name to filter states
     * @param stateName State name to filter states
     * @param pageable Pagination information
     * @return List of states
     */
    @ApiOperation(value = "Get a list of states optionally filtered by country or state name", response = PageResponse.class)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of states"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<PageResponse<StateDTO>> getAllStates(
            @ApiParam(value = "Country name to filter states", required = false) @RequestParam(required = false) String countryName,
            @ApiParam(value = "State name to filter states", required = false) @RequestParam(required = false) String stateName,
            Pageable pageable) {

        log.info("Request received to fetch states - country: {}, state: {}, page: {}, size: {}", countryName, stateName, pageable.getPageNumber(), pageable.getPageSize());
        Page<StateDTO> page = stateService.getStates(countryName, stateName, pageable);
        PageResponse<StateDTO> response = new PageResponse<>(page.getContent(), page.getTotalElements());
        log.info("Responding with {} states out of total {} states", page.getNumberOfElements(), page.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get a state by its ID
     *
     * @param id ID value for the state to be retrieved
     * @return Specific state
     */
    @ApiOperation(value = "Get a state by its ID", response = StateDTO.class)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the state"),
            @ApiResponse(responseCode = "404", description = "State with specified ID not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StateDTO> getStateById(@ApiParam(value = "ID value for the state to be retrieved", required = true) @PathVariable String id) {
        log.info("Fetching state with id: {}", id);
        try {
            StateDTO state = stateService.getStateById(id);
            return new ResponseEntity<>(state, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.error("State with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create a state
     *
     * @param countryId ID value for the country to which the state belongs
     * @param state Data of the state to be created
     * @return Created state
     */
    @ApiOperation(value = "Create a state", response = StateDTO.class)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully created the state"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/country/{countryId}")
    public ResponseEntity<StateDTO> createState(@ApiParam(value = "ID value for the country to which the state belongs", required = true) @PathVariable String countryId, @RequestBody State state) {
        log.info("Creating state for country id: {}", countryId);
        state.setStateId(UUIDUtil.generateUUID());
        StateDTO createdState = stateService.createState(countryId, state);
        return new ResponseEntity<>(createdState, HttpStatus.CREATED);
    }

    /**
     * Update a state
     *
     * @param countryId ID value for the country to which the state belongs
     * @param stateId ID value for the state to be updated
     * @param state New data for the state
     * @return Updated state
     */
    @ApiOperation(value = "Update a state", response = StateDTO.class)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated the state"),
            @ApiResponse(responseCode = "404", description = "State with specified ID not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/country/{countryId}/state/{stateId}")
    public ResponseEntity<StateDTO> updateState(@ApiParam(value = "ID value for the country to which the state belongs", required = true) @PathVariable String countryId,@PathVariable String stateId,@RequestBody State state) {
        log.info("Updating state with id: {} for country id: {}", stateId, countryId);
        try {
            state.setStateId(stateId);
            StateDTO updatedState = stateService.updateState(countryId, state);
            return new ResponseEntity<>(updatedState, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            log.error("State with id: {} not found", stateId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a state
     *
     * @param id ID value for the state to be deleted
     * @return Response with no content
     */
    @ApiOperation(value = "Delete a state")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successfully deleted the state"),
            @ApiResponse(responseCode = "404", description = "State with specified ID not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteState(@ApiParam(value = "ID value for the state to be deleted", required = true) @PathVariable String id) {
        log.info("Deleting state with id: {}", id);
        try {
            stateService.deleteState(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            log.error("State with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
