package com.capstonelegal.task.model.entities;

import com.capstonelegal.employee.model.entities.Employee;
import com.capstonelegal.legalcase.model.entities.LegalCase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "task", schema = "capstonelegalschema", indexes = {
        @Index(name = "idx_task_task_id", columnList = "task_id")
})
public class Task {
    @Id
    @Column(name = "task_id", nullable = false, length = 50)
    private String taskId;

    @Column(name = "task_description", nullable = false, length = 200)
    private String taskDescription;

    @Column(name = "task_created_date", nullable = false)
    private Instant taskCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_legal_case_id", nullable = false)
    private LegalCase taskLegalCase;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_created_by", nullable = false)
    private Employee taskCreatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_last_updated_by")
    private Employee taskLastUpdatedBy;

    @Column(name = "task_last_updated_date")
    private Instant taskLastUpdatedDate;

}