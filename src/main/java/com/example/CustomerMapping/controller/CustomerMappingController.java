package com.example.CustomerMapping.controller;

import com.example.CustomerMapping.entity.ldsdb.CustomerMapping;
import com.example.CustomerMapping.repo.ldsdb.CustomerMappingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer_mapping")
public class CustomerMappingController {

    private final CustomerMappingRepo customerRepo;

    @Autowired
    public CustomerMappingController(CustomerMappingRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping("/findAll")
    public List<CustomerMapping> findAll() {
        return customerRepo.findAll();
    }

    @PostMapping("/insert")
    public CustomerMapping insert(@RequestBody CustomerMapping customerMapping) {
        return customerRepo.save(customerMapping);
    }
}
