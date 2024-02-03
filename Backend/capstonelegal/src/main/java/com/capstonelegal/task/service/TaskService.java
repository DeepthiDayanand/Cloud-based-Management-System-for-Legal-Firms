package com.capstonelegal.task.service;

import com.capstonelegal.employee.model.entities.Employee;
import com.capstonelegal.task.model.dto.TaskDTO;
import com.capstonelegal.task.model.entities.Task;
import com.capstonelegal.task.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;

@Service
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Filters tasks based on provided parameters.
     * The filter parameters are optional, and the method applies filters for the non-null values.
     *
     * @param createdBy  Filter tasks by the "createdBy" column (optional).
     * @param startDate  Filter tasks by the "taskCreatedDate" after this date (optional).
     * @param endDate    Filter tasks by the "taskCreatedDate" before this date (optional).
     * @param updatedBy  Filter tasks by the "taskLastUpdatedBy" column (optional).
     * @param pageable   Pagination and sorting information.
     * @return A Page containing the filtered tasks as TaskDTO.
     * @throws IllegalArgumentException If the startDate is after the endDate.
     */
    public Page<TaskDTO> filterTasks(String createdBy, Instant startDate, Instant endDate, String updatedBy, Pageable pageable) {
        // Validate input parameters
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("startDate cannot be after endDate.");
        }

        Specification<Task> spec = Specification.where(null);

        // Apply filters based on input parameters
        if (!StringUtils.isEmpty(createdBy)) {
            spec = spec.and((root, query, cb) -> {
                Join<Task, Employee> employeeJoin = root.join("taskCreatedBy");
                Predicate predicateFirstName = cb.like(employeeJoin.get("employeeFirstName"), "%" + createdBy + "%");
                Predicate predicateLastName = cb.like(employeeJoin.get("employeeLastName"), "%" + createdBy + "%");
                return cb.or(predicateFirstName, predicateLastName);
            });
        }

        if (startDate != null && endDate != null) {
            spec = spec.and((root, query, cb) -> cb.between(root.get("taskCreatedDate"), startDate, endDate));
        }

        if (!StringUtils.isEmpty(updatedBy)) {
            spec = spec.and((root, query, cb) -> {
                Join<Task, Employee> employeeJoin = root.join("taskLastUpdatedBy");
                Predicate predicateFirstName = cb.like(employeeJoin.get("employeeFirstName"), "%" + updatedBy + "%");
                Predicate predicateLastName = cb.like(employeeJoin.get("employeeLastName"), "%" + updatedBy + "%");
                return cb.or(predicateFirstName, predicateLastName);
            });
        }

        // Fetch and return filtered tasks with pagination and sorting
        Page<Task> filteredTasks = taskRepository.findAll(spec, pageable);
        log.info("Filtered tasks with parameters: createdBy={}, startDate={}, endDate={}, updatedBy={}",
                createdBy, startDate, endDate, updatedBy);

        return filteredTasks.map(task -> TaskDTO.fromEntity(Optional.of(task)));
    }
/**
     * Finds a task by its ID.
     *
     * @param taskId The ID of the task to find.
     * @return The Task entity if found, otherwise Optional.empty().
     * @throws IllegalArgumentException If the provided taskId is null or empty.
     */
    public Optional<Task> getTaskById(@NotNull String taskId) {
        if (StringUtils.isEmpty(taskId)) {
            throw new IllegalArgumentException("taskId cannot be null or empty.");
        }

        log.debug("Finding task with ID: {}", taskId);
        return taskRepository.findById(taskId);
    }

    /**
     * Saves a new task or updates an existing task.
     * If the provided task has a valid ID, it will be considered an update.
     * Otherwise, a new task will be created.
     *
     * @param task The Task entity to save or update.
     * @return The saved or updated Task entity.
     * @throws IllegalArgumentException If the provided task is null or has an invalid ID.
     */
    public Task createTask(@NotNull Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        // Save or update the task entity
        log.debug("Saving task with ID: {}", task.getTaskId());
        return taskRepository.save(task);
    }

    /**
     * Updates an existing task with the provided data.
     * Only the fields that are allowed to be updated will be modified.
     * The last updated date will also be updated.
     *
     * @param taskId The id of the task that need to be updated.
     * @param updatedTask The updated Task entity containing the new data.
     * @return The updated Task entity if the task with the given ID is found, otherwise Optional.empty().
     * @throws IllegalArgumentException If the provided updatedTask is null or has an invalid ID.
     */
    public Optional<Task> updateTask(@NotNull String taskId, @NotNull Task updatedTask) {
        if (updatedTask == null) {
            throw new IllegalArgumentException("Updated task cannot be null.");
        }

        // Find the existing task
        Task existingTask = taskRepository.findById(updatedTask.getTaskId()).orElse(null);
        if (existingTask != null) {
            // Update the fields you want to allow updating
            existingTask.setTaskDescription(updatedTask.getTaskDescription());
            existingTask.setTaskLastUpdatedBy(updatedTask.getTaskLastUpdatedBy());
            existingTask.setTaskLastUpdatedDate(Instant.now()); // Update the last updated date
            log.debug("Updating task with ID: {}", existingTask.getTaskId());
            return Optional.of(taskRepository.save(existingTask));
        }

        log.warn("Task with ID {} not found for update", updatedTask.getTaskId());
        return Optional.empty(); // Task with the given ID not found
    }

    /**
     * Deletes a task by its ID.
     *
     * @param taskId The ID of the task to delete.
     * @throws IllegalArgumentException If the provided taskId is null or empty.
     */
    public void deleteTask(@NotNull String taskId) {
        if (StringUtils.isEmpty(taskId)) {
            throw new IllegalArgumentException("taskId cannot be null or empty.");
        }

        log.debug("Deleting task with ID: {}", taskId);
        taskRepository.deleteById(taskId);
    }
}
