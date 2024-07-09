package com.example.CustomerMapping.controller;


import com.example.CustomerMapping.entity.viviet.CustomerTest;
import com.example.CustomerMapping.repo.viviet.CustomerTestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer_test")
public class CustomerTestController {

    @Autowired
    private CustomerTestRepo customerRepo;

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public List<CustomerTest> findAll() {
        return customerRepo.findAll();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public CustomerTest insert(@RequestBody CustomerTest customerTest) {
        return customerRepo.save(customerTest);
    }
}
