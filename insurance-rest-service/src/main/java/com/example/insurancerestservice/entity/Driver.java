package com.example.insurancerestservice.entity;

import jakarta.persistence.*;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private Integer age;
    private Integer experience;
    private Integer faults;

    private Integer vehicleAge;
    private Double vehiclePurchasePrice;
    private Double vehicleAnnualMileage;

    private Integer insuranceCount;
    private Integer insuranceClaims;

    public Driver() {
        this.id = -1;
        this.name = "";
        this.age = -1;
        this.experience = -1;
        this.faults = -1;
        this.vehicleAge = -1;
        this.vehiclePurchasePrice = -1.0;
        this.vehicleAnnualMileage = -1.0;
        this.insuranceCount = -1;
        this.insuranceClaims = -1;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getFaults() {
        return faults;
    }

    public void setFaults(Integer faults) {
        this.faults = faults;
    }

    public Integer getVehicleAge() {
        return vehicleAge;
    }

    public void setVehicleAge(Integer vehicleAge) {
        this.vehicleAge = vehicleAge;
    }

    public Double getVehiclePurchasePrice() {
        return vehiclePurchasePrice;
    }

    public void setVehiclePurchasePrice(Double vehiclePurchasePrice) {
        this.vehiclePurchasePrice = vehiclePurchasePrice;
    }

    public Double getVehicleAnnualMileage() {
        return vehicleAnnualMileage;
    }

    public void setVehicleAnnualMileage(Double vehicleAnnualMileage) {
        this.vehicleAnnualMileage = vehicleAnnualMileage;
    }

    public Integer getInsuranceCount() {
        return insuranceCount;
    }

    public void setInsuranceCount(Integer insuranceCount) {
        this.insuranceCount = insuranceCount;
    }

    public Integer getInsuranceClaims() {
        return insuranceClaims;
    }

    public void setInsuranceClaims(Integer insuranceClaims) {
        this.insuranceClaims = insuranceClaims;
    }
}

