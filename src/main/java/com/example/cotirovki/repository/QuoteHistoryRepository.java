package com.example.cotirovki.repository;

import com.example.cotirovki.model.Quote;
import com.example.cotirovki.model.QuoteHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteHistoryRepository extends JpaRepository<QuoteHistory, Integer> {
    @Query("SELECT f FROM QuoteHistory f WHERE f.isin = ?1")
    public QuoteHistory findByIsinHist(String isin);

    @Query("SELECT f FROM QuoteHistory f WHERE f.isin = ?1 AND f.isActual = true")
    public QuoteHistory findByIsinHistWithTrue(String isin);

}
