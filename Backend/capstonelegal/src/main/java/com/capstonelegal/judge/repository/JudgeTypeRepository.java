package com.capstonelegal.judge.repository;
import com.capstonelegal.judge.model.entities.JudgeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JudgeTypeRepository extends JpaRepository<JudgeType, String>, JpaSpecificationExecutor<JudgeType> {
}
