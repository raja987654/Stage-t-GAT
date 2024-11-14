package com.example.insurancerestservice.controller;

import com.example.insurancerestservice.entity.Quote;
import com.example.insurancerestservice.service.InsuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/quotes")
public class QuoteController {
    private final InsuranceService insuranceService;

    public QuoteController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    /**
     * This GET endpoint retrieves a Quote object for a given reference id if found.
     *
     * @param reference reference id of quote to be retrieved
     * @return Optional Quote object if found
     */
    @GetMapping(params = "reference")
    public Optional<Quote> getQuoteByReference(@RequestParam(name="reference") String reference) {
        return insuranceService.getQuoteByReference(reference);
    }
}