package com.example.CustomerMapping.after.controller;

import com.example.CustomerMapping.after.entity.CustomerMapping;
import com.example.CustomerMapping.after.repo.CustomerMappingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer_mapping")
public class CustomerMappingController {

    @Autowired
    private CustomerMappingRepo customerRepo;

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<CustomerMapping> findAll() {
        return customerRepo.findAll();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public CustomerMapping insert(@RequestBody CustomerMapping customerMapping) {
        return customerRepo.save(customerMapping);
    }
}
