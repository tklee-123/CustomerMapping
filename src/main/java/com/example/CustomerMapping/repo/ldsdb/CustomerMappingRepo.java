package com.example.CustomerMapping.repo.ldsdb;

import com.example.CustomerMapping.entity.ldsdb.CustomerMapping;
import org.springframework.data.jpa.repository.JpaRepository;

// Ensure the repository is annotated
public interface CustomerMappingRepo extends JpaRepository<CustomerMapping, String> {
}
