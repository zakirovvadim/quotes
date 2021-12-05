package ru.vadim.quotes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import ru.vadim.quotes.model.Quote;

import java.util.Optional;

@Repository
@Component
public interface QuotesRepository extends JpaRepository<Quote, Long> {
    @Query("SELECT f FROM Quote f WHERE f.isin = :isin AND f.isActual = true")
    Optional<Quote> findByIsinWithActualTrue(@Param("isin")String isin);

}
