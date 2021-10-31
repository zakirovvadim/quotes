package com.example.cotirovki.repository;

import com.example.cotirovki.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    @Query("SELECT f FROM Quote f WHERE f.isin = ?1")
    public Optional<Quote> findByIsin(String isin);
}
