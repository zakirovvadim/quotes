package ru.vadim.quotes.service;


import lombok.RequiredArgsConstructor;
import ru.vadim.quotes.dto.QuoteDto;
import ru.vadim.quotes.exceptions.NoEntityException;
import ru.vadim.quotes.exceptions.ValidationErrorException;
import ru.vadim.quotes.mappers.QuoteMapper;
import ru.vadim.quotes.model.Quote;
import ru.vadim.quotes.repository.QuotesRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuotesRepository repository;
    private final QuoteMapper mapper;

    public List<QuoteDto> findAll() {
        List<Quote> quoteList = repository.findAll();
        List<QuoteDto> dtoList = mapper.map(quoteList);
        return dtoList;
    }

    public QuoteDto findByIsin(String isin) {
        Quote quote = null;
        Optional<Quote> optionalQuote = repository.findByIsinWithActualTrue(isin);
        if (optionalQuote.isPresent()) {
            quote = optionalQuote.get();
        } else {
            throw new NoEntityException("There is no quote with ISIN = " + isin + " in Data");
        }
        QuoteDto dto = mapper.map(quote);
        return dto;
    }

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
            } else {
                newQuote.setActual(true);
                newQuote.setElvl(newQuote.getBid());
                saveWithCalculateElvl(newQuote);
            }

        } else {
            throw new ValidationErrorException("The quote doesn't right length or BID less than ASK");
        }
    }

    public void saveWithCalculateElvl(Quote newQuote, Quote currentQuote) {
        if (isAskOfNewQuoteMoreThanCurrentElvl(newQuote, currentQuote)) {
            newQuote.setElvl(currentQuote.getAsk());
            repository.save(newQuote);
            return;
        } else if (isBidOfNewQuoteMoreThanElvlOfCurrent(newQuote, currentQuote)) {
            newQuote.setElvl(currentQuote.getBid());
            repository.save(newQuote);
            return;
        }
        repository.save(newQuote);
    }

    public void saveWithCalculateElvl(Quote newQuote) {
        if (isAskMoreThanBidOrBidIsNullOrZero(newQuote)) {
            newQuote.setElvl(newQuote.getAsk());
            repository.save(newQuote);
            return;
        } else if (isBidMoreThanElvlOrElvlIsNull(newQuote)) {
            newQuote.setElvl(newQuote.getBid());
            repository.save(newQuote);
            return;
        }
        repository.save(newQuote);
    }

    public boolean isAskMoreThanBidOrBidIsNullOrZero(Quote newQuote) {
        if ((newQuote.getAsk().compareTo(newQuote.getElvl()) < 0)
                || newQuote.getBid() == null
                || newQuote.getBid().compareTo(new BigDecimal(0)) == 0) {
            return true;
        }
        else {return false;}
    }
    public boolean isBidMoreThanElvlOrElvlIsNull(Quote newQuote) {
        if ((newQuote.getBid().compareTo(newQuote.getElvl()) > 0)
                || newQuote.getElvl() == null
                || newQuote.getElvl().compareTo(new BigDecimal(0)) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAskOfNewQuoteMoreThanCurrentElvl(Quote newQuote, Quote currentQuote) {
        if ((newQuote.getAsk().compareTo(currentQuote.getElvl()) < 0)
                || newQuote.getBid() == null) {
            return true;
        } else {
            return false;
        }
    }
    //
    public boolean isBidOfNewQuoteMoreThanElvlOfCurrent(Quote newQuote, Quote currentQuote){
        if ((newQuote.getBid().compareTo(currentQuote.getElvl()) > 0)
                || newQuote.getElvl() == null) {
            return true;
        } else {
            return false;
        }
    }
}
