package com.capstonelegal.court.repository;

import com.capstonelegal.court.model.entities.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface CourtRepository extends JpaRepository<Court, String>, JpaSpecificationExecutor<Court> {
}
