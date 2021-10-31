package com.example.cotirovki.service;

import com.example.cotirovki.exceptions.ValidationErrorException;
import com.example.cotirovki.model.Quote;
import com.example.cotirovki.model.QuoteHistory;
import com.example.cotirovki.repository.QuoteHistoryRepository;
import com.example.cotirovki.repository.QuoteRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class QuoteService {
    private final QuoteRepository repository;

}
