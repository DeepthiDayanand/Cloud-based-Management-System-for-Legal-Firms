package com.capstonelegal.judge.controller;

import com.capstonelegal.judge.model.entities.JudgeType;
import com.capstonelegal.judge.service.JudgeTypeService;
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
 * REST controller for managing {@link JudgeType}.
 */
@RestController
@RequestMapping("/v1/judgetypes")
@Slf4j
@Api(value = "JudgeType Controller", tags = "08 - Judge Type Management",description = "Operations pertaining to judge types in Capstone Legal")
public class JudgeTypeController {

    private final JudgeTypeService judgeTypeService;

    @Autowired
    public JudgeTypeController(JudgeTypeService judgeTypeService) {
        this.judgeTypeService = judgeTypeService;
    }

    /**
     * Fetches and returns all judge types.
     *
     * @return A list of all judge types.
     */
    @ApiOperation(value = "View a list of available judge types", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of judge types")
    })
    @GetMapping
    public ResponseEntity<List<JudgeType>> getAllJudgeTypes() {
        log.info("Fetching all judge types");
        List<JudgeType> judgeTypes = judgeTypeService.getAllJudgeTypes();
        return new ResponseEntity<>(judgeTypes, HttpStatus.OK);
    }

    /**
     * Fetches and returns the judge type with the specified ID.
     *
     * @param id ID of the judge type to retrieve.
     * @return The judge type with the specified ID, or 404 status if not found.
     */
    @ApiOperation(value = "Get a judge type by its ID", response = JudgeType.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved judge type"),
            @ApiResponse(responseCode = "404", description = "Judge type not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<JudgeType> getJudgeTypeById(@ApiParam(value = "JudgeType ID to fetch", required = true) @PathVariable String id) {
        log.info("Fetching judge type with ID: {}", id);
        JudgeType judgeType = judgeTypeService.getJudgeTypeById(id);
        if (judgeType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(judgeType, HttpStatus.OK);
    }

    /**
     * Creates or updates a judge type.
     *
     * @param judgeType The judge type information to create or update.
     * @return The created or updated judge type.
     */
    @ApiOperation(value = "Create or update a judge type", response = JudgeType.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created or updated judge type"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<JudgeType> createOrUpdateJudgeType(@RequestBody JudgeType judgeType) {
        log.info("Creating or updating judge type: {}", judgeType);
        JudgeType createdOrUpdatedJudgeType = judgeTypeService.createOrUpdateJudgeType(judgeType);
        return new ResponseEntity<>(createdOrUpdatedJudgeType, HttpStatus.CREATED);
    }

    /**
     * Deletes the judge type with the specified ID.
     *
     * @param id ID of the judge type to delete.
     * @return Response with HTTP status code.
     */
    @ApiOperation(value = "Delete a judge type by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted judge type"),
            @ApiResponse(responseCode = "404", description = "Judge type not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJudgeType(@ApiParam(value = "JudgeType ID to delete", required = true) @PathVariable String id) {
        log.info("Deleting judge type with ID: {}", id);
        JudgeType judgeType = judgeTypeService.getJudgeTypeById(id);
        if (judgeType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        judgeTypeService.deleteJudgeType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
