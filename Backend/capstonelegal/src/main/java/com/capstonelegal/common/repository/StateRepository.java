package com.capstonelegal.common.repository;

import com.capstonelegal.common.model.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, String>, JpaSpecificationExecutor<State> {
}

