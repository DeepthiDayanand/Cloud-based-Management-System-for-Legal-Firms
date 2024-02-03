package com.capstonelegal.court.repository;

import com.capstonelegal.court.model.entities.CourtType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtTypeRepository extends JpaRepository<CourtType, String>, JpaSpecificationExecutor<CourtType> {
}
