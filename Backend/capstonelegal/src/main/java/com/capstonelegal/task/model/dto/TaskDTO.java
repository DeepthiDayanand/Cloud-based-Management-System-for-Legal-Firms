package com.capstonelegal.task.model.dto;

import com.capstonelegal.employee.model.entities.Employee;
import com.capstonelegal.legalcase.model.entities.LegalCase;
import com.capstonelegal.task.model.entities.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;

/**
 * Data Transfer Object (DTO) for {@link Task} entity.
 * This class represents a simplified view of the {@link Task} entity,
 * which is used to transfer data between the service layer and the frontend.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDTO implements Serializable {
    private String taskId;
    private String taskDescription;
    private Instant taskCreatedDate;
    private LegalCase taskLegalCase;
    private Employee taskCreatedBy;
    private Employee taskLastUpdatedBy;
    private Instant taskLastUpdatedDate;
    /**
     * Static method to convert a {@link Task} entity object to a {@link TaskDTO} object.
     *
     * @param task The {@link Task} entity object to convert.
     * @return A new {@link TaskDTO} object with data from the provided {@link Task} entity.
     */
    public static TaskDTO fromEntity(Optional<Task> task) {
        return task.map(taskEntity -> {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTaskId(taskEntity.getTaskId());
            taskDTO.setTaskDescription(taskEntity.getTaskDescription());
            taskDTO.setTaskCreatedDate(taskEntity.getTaskCreatedDate());
            taskDTO.setTaskLegalCase(taskEntity.getTaskLegalCase());
            taskDTO.setTaskCreatedBy(taskEntity.getTaskCreatedBy());
            taskDTO.setTaskLastUpdatedBy(taskEntity.getTaskLastUpdatedBy());
            taskDTO.setTaskLastUpdatedDate(taskEntity.getTaskLastUpdatedDate());
            return taskDTO;
        }).orElse(null);
    }
}