package com.example.CustomerMapping.before.repo;

import com.example.CustomerMapping.before.entity.CustomerTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerTestRepo extends JpaRepository<CustomerTest, Long> {
}
