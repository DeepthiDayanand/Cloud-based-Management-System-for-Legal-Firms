package com.capstonelegal.task.controller;
import com.capstonelegal.common.model.PageResponse;
import com.capstonelegal.task.model.entities.Task;
import com.capstonelegal.util.UUIDUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.capstonelegal.task.model.dto.TaskDTO;
import com.capstonelegal.task.service.TaskService;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/capstonelegal/v1/tasks")
@Slf4j
@Api(tags = "Task API")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    /**
     * API endpoint to filter tasks based on various criteria.
     *
     * @param createdBy Filter tasks by the "createdBy" column (optional).
     * @param startDate Filter tasks by the "taskCreatedDate" after this date (optional).
     * @param endDate   Filter tasks by the "taskCreatedDate" before this date (optional).
     * @param updatedBy Filter tasks by the "taskLastUpdatedBy" column (optional).
     * @param page      Page number (starts from 0).
     * @param size      Number of tasks to return per page.
     * @return A paginated list of filtered tasks.
     */
    @ApiOperation("Filter tasks based on various criteria")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved filtered tasks"),
            @ApiResponse(code = 400, message = "Invalid input parameters"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<PageResponse<TaskDTO>> filterTasks(
            @ApiParam(value = "Filter tasks by createdBy") @RequestParam(required = false) String createdBy,
            @ApiParam(value = "Filter tasks by taskCreatedDate after this date") @RequestParam(required = false) Instant startDate,
            @ApiParam(value = "Filter tasks by taskCreatedDate before this date") @RequestParam(required = false) Instant endDate,
            @ApiParam(value = "Filter tasks by updatedBy") @RequestParam(required = false) String updatedBy,
            @ApiParam(value = "Page number (starts from 0)") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "Number of tasks to return per page") @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        try {
            Page<TaskDTO> tasks = taskService.filterTasks(createdBy, startDate, endDate, updatedBy, pageable);
            PageResponse<TaskDTO> response = new PageResponse<>(tasks.getContent(), tasks.getTotalElements());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error("Error while filtering tasks: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while filtering tasks", e);
        }
    }

    /**
     * API endpoint to retrieve a task by its ID.
     *
     * @param taskId The ID of the task to retrieve.
     * @return The TaskDTO if the task is found, or a 404 Not Found response.
     */
    @ApiOperation("Get a task by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the task"),
            @ApiResponse(code = 404, message = "Task not found")
    })
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(
            @ApiParam(value = "ID of the task to retrieve", required = true) @PathVariable String taskId
    ) {
        try {
            TaskDTO task = TaskDTO.fromEntity(taskService.getTaskById(taskId));
            return ResponseEntity.ok(task);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error("Error while retrieving task: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while retrieving task", e);
        }
    }
/*
    /**
     * API endpoint to create a new task.
     *
     * @param employeeId   The employeeId to associate with the new task.
     * @param legalCaseId  The legalCaseId to associate with the new task.
     * @param task         The Task representing the new task to create.
     * @return The created TaskDTO.
     */
 /*   @ApiOperation("Create a new task")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created the task"),
            @ApiResponse(code = 400, message = "Invalid input for creating task"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/employee/{employeeId}/legalcase/{legalCaseId}")
    public ResponseEntity<TaskDTO> createTask(
            @ApiParam(value = "Employee ID to associate with the new task", required = true) @PathVariable String employeeId,
            @ApiParam(value = "Legal Case ID to associate with the new task", required = true) @PathVariable String legalCaseId,
            @ApiParam(value = "Task data for creating a new task", required = true) @Valid @RequestBody Task task
    ) {
        try {
            task.setTaskId(UUIDUtil.generateUUID());
            TaskDTO createdTask = TaskDTO.fromEntity(Optional.ofNullable(taskService.createTask(employeeId, legalCaseId, task)));
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error("Error while creating task: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while creating task", e);
        }
    }
*/
    @ApiOperation("Update an existing task")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the task"),
            @ApiResponse(code = 400, message = "Invalid input for updating task"),
            @ApiResponse(code = 404, message = "Task not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(
            @ApiParam(value = "ID of the task to update", required = true) @PathVariable String taskId,
            @ApiParam(value = "Task data for updating an existing task", required = true) @Valid @RequestBody Task task
    ) {
        try {
            TaskDTO updatedTask = TaskDTO.fromEntity(taskService.updateTask(taskId, task));
            if(updatedTask == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
            return ResponseEntity.ok(updatedTask);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error("Error while updating task: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while updating task", e);
        }
    }

    @ApiOperation("Delete a task by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted the task"),
            @ApiResponse(code = 400, message = "Invalid taskId for deleting task"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @ApiParam(value = "ID of the task to delete", required = true) @PathVariable String taskId
    ) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error("Error while deleting task: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while deleting task", e);
        }
    }
}
