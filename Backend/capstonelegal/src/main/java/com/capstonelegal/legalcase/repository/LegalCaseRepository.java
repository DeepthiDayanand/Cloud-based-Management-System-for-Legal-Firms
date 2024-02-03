package com.capstonelegal.legalcase.repository;

import com.capstonelegal.legalcase.model.entities.LegalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalCaseRepository extends JpaRepository<LegalCase, String>, JpaSpecificationExecutor<LegalCase> {
}
