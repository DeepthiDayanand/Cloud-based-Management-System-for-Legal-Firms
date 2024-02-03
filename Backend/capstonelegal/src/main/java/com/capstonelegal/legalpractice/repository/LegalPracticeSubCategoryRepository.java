package com.capstonelegal.legalpractice.repository;

import com.capstonelegal.legalpractice.model.entities.LegalPracticeSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface LegalPracticeSubCategoryRepository  extends JpaRepository<LegalPracticeSubCategory, String>, JpaSpecificationExecutor<LegalPracticeSubCategory> {
}

