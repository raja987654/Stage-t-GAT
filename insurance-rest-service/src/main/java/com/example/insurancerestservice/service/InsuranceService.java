package com.example.insurancerestservice.service;

import com.example.insurancerestservice.entity.Driver;
import com.example.insurancerestservice.entity.Quote;
import com.example.insurancerestservice.repository.DriverRepository;
import com.example.insurancerestservice.repository.QuoteRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class InsuranceService {
    private final RestTemplate restTemplate;

    private final DriverRepository driverRepository;
    private final QuoteRepository quoteRepository;


    public InsuranceService(RestTemplate restTemplate, DriverRepository driverRepository, QuoteRepository quoteRepository) {
        this.restTemplate = restTemplate;
        this.driverRepository = driverRepository;
        this.quoteRepository = quoteRepository;
    }

    /**
     * This method retrieves a Quote (entity) object from its repository if it exists.
     *
     * @param reference reference if of Quote (entity) object to be retrieved
     * @return Optional Quote object if found
     */
    public Optional<Quote> getQuoteByReference(String reference) {
        return quoteRepository.findById(reference);
    }

    /**
     * This method creates a new quote by breaking down the information provided in the Driver object.
     * Various parameter are analyzed to calculate the insurance factor.
     * <p>
     * This insurance factor is multiplied by a base premium to calculate the final annual insurance.
     * The newly created Quote object is linked to its corresponding Driver object by id.
     * <p>
     * For certain parameters, it is possible that quote cannot be calculated. In such cases, the Quote
     * will have a false success value and users should be redirected to a specialist for custom quotes.
     *
     * @param driver contains driver information parameters to be used for insurance premium calculations
     * @return reference id of newly created quote object
     */
    public String createQuote(Driver driver) {
        Quote quote = new Quote();

        // new quote will be linked to the driver's information
        quote.setDriverId(driver.getId());





        // base premium retrieved from this API
        String apiUrl = "https://storage.googleapis.com/connex-th/insurance_assignment/base_premium.json";

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(response.getBody(), JsonObject.class);
        double basePremium = jsonObj.get("base_premium").getAsDouble();
        double insuranceFactor = 0.0;

        // factor 1: age
        double ageFactor = 0.0;
        Integer age = driver.getAge();
        if (age < 21) {
            quote.setSuccess(false); // Nécessite une évaluation spéciale pour les jeunes conducteurs
        } else if (age < 25) {
            ageFactor = 1.4;
        } else if (age < 35) {
            ageFactor = 1.1;
        } else if (age < 60) {
            ageFactor = 1.0;
        } else if (age < 70) {
            ageFactor = 1.2;
        } else {
            quote.setSuccess(false);
        }

        // factor 2: driving experience
        double drivingExperienceFactor = 0.0;
        Integer drivingExperience = driver.getExperience();
        if (drivingExperience < 1) {
            drivingExperienceFactor = 1.6;
        } else if (drivingExperience < 3) {
            drivingExperienceFactor = 1.4;
        } else if (drivingExperience < 8) {
            drivingExperienceFactor = 1.2;
        } else {
            drivingExperienceFactor = 1.0;
        }
        // factor 3: faults in the last 5 years
        double driverRecordFactor = 0.0;
        Integer trafficViolations = driver.getFaults();
        if (trafficViolations == 0) {
            driverRecordFactor = 0.9;
        } else if (trafficViolations == 1) {
            driverRecordFactor = 1.2;
        } else if (trafficViolations == 2) {
            driverRecordFactor = 1.4;
        } else {
            quote.setSuccess(false);
        }

        // factor 4: insurance claims
        double claimsFactor = 0.0;
        Integer claims = driver.getInsuranceClaims();
        if (claims == 0) {
            claimsFactor = 0.8;
        } else if (claims == 1) {
            claimsFactor = 1.3;
        } else if (claims == 2) {
            claimsFactor = 1.6;
        } else {
            quote.setSuccess(false);
        }


        // factor 5: car value after depreciation
        double carValueFactor = 0.0;
        Double carValue = driver.getVehiclePurchasePrice();
        // Dépréciation adaptée au marché tunisien
        for (int i = 1; i <= driver.getVehicleAge(); i++) {
            if (i <= 2) {
                carValue -= carValue * 0.20; // Dépréciation plus importante les premières années
            } else {
                carValue -= carValue * 0.15;
            }
        }
        if (carValue < 20000.0) {
            carValueFactor = 0.8;
        } else if (carValue < 40000.0) {
            carValueFactor = 1.0;
        } else if (carValue < 60000.0) {
            carValueFactor = 1.3;
        } else if (carValue < 80000.0) {
            carValueFactor = 1.6;
        } else if (carValue < 100000.0) {
            carValueFactor = 2.0;
        } else {
            quote.setSuccess(false);
        }
        // Facteur 6: kilométrage annuel (adapté aux distances tunisiennes)
        double mileageFactor = 0.0;
        Double annualMileage = driver.getVehicleAnnualMileage();
        if (annualMileage < 15000.0) {
            mileageFactor = 0.9;
        } else if (annualMileage < 25000.0) {
            mileageFactor = 1.0;
        } else if (annualMileage < 40000.0) {
            mileageFactor = 1.2;
        } else {
            mileageFactor = 1.4;
        }

        // factor 7: number of insurances
        double insuranceHistoryFactor = 0.0;
        Integer insuranceHistory = driver.getInsuranceCount();
        if (insuranceHistory == 0) {
            insuranceHistoryFactor = 1.3;
        } else if (insuranceHistory == 1) {
            insuranceHistoryFactor = 1.1;
        } else if (insuranceHistory >= 2) {
            insuranceHistoryFactor = 0.9; // Bonus pour fidélité
        }

        if (quote.getSuccess()) {
            insuranceFactor = ageFactor * drivingExperienceFactor * driverRecordFactor * claimsFactor *
                    carValueFactor * mileageFactor * insuranceHistoryFactor;
        }

// Prime finale en TND
        double premium = basePremium * insuranceFactor;

        // Ajout d'une taxe spécifique au marché tunisien
        premium = premium * 1.19; // TVA tunisienne de 19%

        quote.setPremium(premium);

        driverRepository.save(driver);
        quoteRepository.save(quote);

        return quote.getReference();
    }
}
