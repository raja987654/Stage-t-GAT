package com.example.insurancerestservice.entity;

import jakarta.persistence.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Quote {
    @Id
    private String reference;
    private Boolean success;
    private Double premium;
    private String timestamp;

    // links Quote to specific Driver
    private long driverId;

    public Quote() {
        // generates a random 16 character alphanumeric string starting with 'Q'
        this.reference = 'Q' + RandomStringUtils.randomAlphanumeric(15).toUpperCase();

        this.success = true;
        this.premium = 0.0;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        this.timestamp = simpleDateFormat.format(new Date());
    }

    public String getReference() {
        return reference;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "reference='" + reference + '\'' +
                ", success=" + success +
                ", premium=" + premium +
                ", timestamp=" + timestamp +
                ", driverId=" + driverId +
                '}';
    }
}
