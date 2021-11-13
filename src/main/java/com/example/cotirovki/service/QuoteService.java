package com.example.cotirovki.service;


import com.example.cotirovki.dto.QuoteDTO;
import com.example.cotirovki.exceptions.NoEntityException;
import com.example.cotirovki.exceptions.ValidationErrorException;
import com.example.cotirovki.model.Quote;
import com.example.cotirovki.repository.QuotesRepository;
import com.example.cotirovki.utils.MappingUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuoteService {
    private final QuotesRepository repository;

    public List<Quote> findAll() {
        return repository.findAll();
    }

    public QuoteDTO findByIsin(String isin) {
        Quote quote = null;
        Optional<Quote> optionalQuote = repository.findByIsin(isin);
        if (optionalQuote.isPresent()) {
            quote = optionalQuote.get();
        } else {
            throw new NoEntityException("There is no quote with ISIN = " + isin + " in Data");
        }

        QuoteDTO dto = new MappingUtils().mapToDto(quote);
        return dto;
    }
// не устанавливает айди
    public void save(Quote newQuote) {
        if ((newQuote.getBid().compareTo(newQuote.getAsk()) == -1) && newQuote.getIsin().length() == 12) {
            Quote currentQuote = null;
            Optional<Quote> optional = repository.findByIsinWithActualTrue(newQuote.getIsin());
            if (optional.isPresent()) {
                currentQuote = optional.get();
                currentQuote.setActual(false); // не сохраняет изменение в бд
                newQuote.setActual(true);
                newQuote.setElvl(newQuote.getBid());
                saveWithCalculateElvl(newQuote, currentQuote);
            } else  {
                newQuote.setActual(true);
                newQuote.setElvl(newQuote.getBid());
                repository.save(newQuote);
            }

        } else {
            throw new ValidationErrorException("The quote doesn't right length or BID less than ASK");
        }
    }

    public void saveWithCalculateElvl(Quote newQuote, Quote currentQuote) {
        if ((newQuote.getAsk().compareTo(currentQuote.getElvl()) == -1) || newQuote.getBid() == null) {
            newQuote.setElvl(currentQuote.getAsk());
            repository.save(newQuote);
            return;
        } else if ((newQuote.getBid().compareTo(currentQuote.getElvl()) == 1) || newQuote.getElvl() == null) {
            newQuote.setElvl(currentQuote.getBid());
            repository.save(newQuote);
            return;
        }
        repository.save(newQuote);
    }
}
