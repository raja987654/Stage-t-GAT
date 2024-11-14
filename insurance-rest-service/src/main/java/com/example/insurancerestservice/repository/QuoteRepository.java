package com.example.insurancerestservice.repository;

import com.example.insurancerestservice.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, String> {}