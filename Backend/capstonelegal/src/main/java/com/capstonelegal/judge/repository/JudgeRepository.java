package com.capstonelegal.judge.repository;

import com.capstonelegal.judge.model.entities.Judge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JudgeRepository extends JpaRepository<Judge, String>, JpaSpecificationExecutor<Judge> {
}
