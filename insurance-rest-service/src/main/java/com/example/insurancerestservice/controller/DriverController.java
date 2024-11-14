package com.example.insurancerestservice.controller;

import com.example.insurancerestservice.entity.Driver;
import com.example.insurancerestservice.service.InsuranceService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DriverController {
    private final InsuranceService insuranceService;

    public DriverController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    /**
     * This POST endpoint creates and saves a new Quote object based on the provided Driver information object
     * and returns the reference id of the newly create quote.
     *
     * @param driver contains information about the driver
     * @return reference id of newly created quote
     */
    @PostMapping("/calculate")
    public String calculateInsurance(@RequestBody Driver driver) {
        return insuranceService.createQuote(driver);
    }
}