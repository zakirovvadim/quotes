package com.example.cotirovki.service;

import com.example.cotirovki.model.QuoteHistory;
import com.example.cotirovki.repository.QuoteHistoryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
public class QuoteHistoryService {
    private final QuoteHistoryRepository repository;

    public QuoteHistory save(QuoteHistory quoteHistory) {
        return repository.save(quoteHistory);
    }
}
