package com.capstonelegal.judge.controller;

import com.capstonelegal.judge.model.DTO.JudgeDTO;
import com.capstonelegal.judge.service.JudgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/v1/judges")
@Validated
@Api(value = "JudgeType Controller", tags = "09 - Judge Management",description = "Operations pertaining to judges in Capstone Legal")
@Slf4j
public class JudgeController {

    private final JudgeService judgeService;

    @Autowired
    public JudgeController(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    /**
     * Retrieve judges with optional filtering by various parameters.
     *
     * @param countryName Optional country name to filter judges by.
     * @param stateName Optional state name to filter judges by.
     * @param districtName Optional district name to filter judges by.
     * @param cityName Optional city name to filter judges by.
     * @param courtName Optional court name to filter judges by.
     * @param pageable Pageable object for pagination.
     * @return ResponseEntity containing a Page of JudgeView objects with the filtered results.
     */
    @GetMapping
    @ApiOperation(value = "Get judges with filtering", notes = "Retrieves judges with optional filtering by country, state, district, city, and court names.")
    public ResponseEntity<Page<JudgeDTO>> getJudgesByFilter(
            @RequestParam(required = false) String countryName,
            @RequestParam(required = false) String stateName,
            @RequestParam(required = false) String districtName,
            @RequestParam(required = false) String cityName,
            @RequestParam(required = false) String courtName,
            Pageable pageable) {

        log.info("Fetching judges with filters: country={}, state={}, district={}, city={}, court={}",
                countryName, stateName, districtName, cityName, courtName);

        Page<JudgeDTO> judges = judgeService.getJudgesByFilters(pageable, cityName, districtName, stateName,
                countryName, courtName, null);
        return ResponseEntity.ok(judges);
    }

    /**
     * Retrieve a specific judge by ID.
     *
     * @param id The ID of the judge to retrieve.
     * @return ResponseEntity containing the JudgeView object with the specified ID or 404 Not Found if not found.
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Get judge by ID", notes = "Retrieves a specific judge by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved judge"),
            @ApiResponse(responseCode = "404", description = "Judge not found with the specified ID")
    })
    public ResponseEntity<JudgeDTO> getJudgeById(
            @PathVariable @ApiParam(value = "ID of the judge", required = true) String id) {

        log.info("Fetching judge with ID: {}", id);

        JudgeDTO judge = judgeService.getJudgeById(id);
        if (judge != null) {
            return new ResponseEntity<>(judge, HttpStatus.OK);
        } else {
            log.warn("Judge with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a judge by ID.
     *
     * @param id The ID of the judge to delete.
     * @return ResponseEntity with 204 No Content on success, or 404 Not Found if the judge with the specified ID is not found.
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a judge by ID", notes = "Deletes a judge with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Judge successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Judge not found with the specified ID")
    })
    public ResponseEntity<Void> deleteJudge(
            @PathVariable @ApiParam(value = "ID of the judge", required = true) String id) {

        log.info("Deleting judge with ID: {}", id);

        try {
            judgeService.deleteJudge(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException ex) {
            log.warn("Error deleting judge with ID: {}", id, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
