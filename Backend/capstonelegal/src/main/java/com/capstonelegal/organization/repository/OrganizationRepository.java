package com.capstonelegal.organization.repository;

import com.capstonelegal.organization.model.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface OrganizationRepository  extends JpaRepository<Organization, String>, JpaSpecificationExecutor<Organization> {
}
