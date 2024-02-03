package com.capstonelegal.legaldocument.repository;

import com.capstonelegal.legaldocument.model.entities.LegalDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface LegalDocumentRepository  extends JpaRepository<LegalDocument, String>, JpaSpecificationExecutor<LegalDocument> {
}
