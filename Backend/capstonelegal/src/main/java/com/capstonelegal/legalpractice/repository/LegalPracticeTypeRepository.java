package com.capstonelegal.legalpractice.repository;

import com.capstonelegal.legalpractice.model.entities.LegalPracticeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface LegalPracticeTypeRepository  extends JpaRepository<LegalPracticeType, String>, JpaSpecificationExecutor<LegalPracticeType> {
}
