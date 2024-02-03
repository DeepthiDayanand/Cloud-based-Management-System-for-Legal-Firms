package com.capstonelegal.credentials.repository;

import com.capstonelegal.credentials.model.entities.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface CredentialsRepository  extends JpaRepository<Credentials, String>, JpaSpecificationExecutor<Credentials> {
}
