package com.example.CustomerMapping.after.repo;

import com.example.CustomerMapping.after.entity.CustomerMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerMappingRepo extends JpaRepository<CustomerMapping, String> {
}
