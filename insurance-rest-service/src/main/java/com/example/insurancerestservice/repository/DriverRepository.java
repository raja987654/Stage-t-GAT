package com.example.insurancerestservice.repository;

import com.example.insurancerestservice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {}
