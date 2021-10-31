package com.example.cotirovki.utils;

import com.example.cotirovki.model.Quote;
import com.example.cotirovki.service.QuoteAndHistoryService;
import com.example.cotirovki.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class InitialUtil implements CommandLineRunner {
    private final QuoteAndHistoryService quoteService;
    @Override
    public void run(String... args) throws Exception {
        //Quote quote1 = new Quote("RU000A0JX0K9", new BigDecimal("105.1"), new BigDecimal("107.4"));
        //Quote quote2 = new Quote("RU001A0JX0K9", new BigDecimal("105.7"), new BigDecimal("109.2"));
        //Quote quote3 = new Quote("RU002A0JX0K9", new BigDecimal("104.7"), new BigDecimal("108.2"));
        //quoteService.save(quote1);
        //quoteService.save(quote2);
        //quoteService.save(quote3);

//        for(Quote e : quoteService.findAll()){
//            System.out.println(e.toString());
//        }
    }
}
