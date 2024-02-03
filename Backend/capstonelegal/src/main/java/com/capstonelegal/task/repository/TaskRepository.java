package com.capstonelegal.task.repository;

import com.capstonelegal.task.model.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskRepository  extends JpaRepository<Task, String>, JpaSpecificationExecutor<Task> {
}

