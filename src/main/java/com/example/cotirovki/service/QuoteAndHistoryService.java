package com.example.cotirovki.service;

import com.example.cotirovki.exceptions.NoEntityException;
import com.example.cotirovki.exceptions.ValidationErrorException;
import com.example.cotirovki.model.Quote;
import com.example.cotirovki.model.QuoteHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteAndHistoryService {
    private final QuoteHistoryService quoteHistoryService;
    private final QuoteService quoteService;

    public List<Quote> findAll() {
        return quoteService.getRepository().findAll();
    }

    public Quote findByIsin(String isin) {
        return quoteService.getRepository().findByIsin(isin).orElseThrow(NoEntityException::new);
    }

    public Quote save(Quote quote) {
        try {
            if ((quote.getBid().compareTo(quote.getAsk()) == -1) && quote.getIsin().length() == 12) {
                boolean quote2 = quoteService.getRepository().findByIsin(quote.getIsin()).isPresent();
                if (!quote2) {
                    QuoteHistory quoteHistory = QuoteHistory.builder()
                            .isin(quote.getIsin())
                            .bid(quote.getBid())
                            .ask(quote.getAsk())
                            .elvl(quote.getElvl())
                            .isActual(true)
                            .build();
                    quoteHistoryService.save(quoteHistory);
                    return quoteService.getRepository().save(quote);
                } else {
                    Quote quote1 = quoteService.getRepository().findByIsin(quote.getIsin()).get();
                    if ((quote.getBid().compareTo(quote1.getElvl()) == 1) || quote.getElvl() == null) {
                        quote.setElvl(quote.getBid());
                        QuoteHistory quoteHistory = QuoteHistory.builder()
                                .isin(quote.getIsin())
                                .bid(quote.getBid())
                                .ask(quote.getAsk())
                                .elvl(quote.getElvl())
                                .isActual(true)
                                .build();
                        delete(quote1.getIsin());
                        quoteHistoryService.save(quoteHistory);
                        return quoteService.getRepository().save(quote);
                    }
                    if((quote.getAsk().compareTo(quote1.getElvl()) == -1) || quote.getBid() == null) {
                        quote.setElvl(quote.getAsk());
                        QuoteHistory quoteHistory = QuoteHistory.builder()
                                .isin(quote.getIsin())
                                .bid(quote.getBid())
                                .ask(quote.getAsk())
                                .elvl(quote.getElvl())
                                .isActual(true)
                                .build();
                        delete(quote1.getIsin());
                        quoteHistoryService.save(quoteHistory);
                        return quoteService.getRepository().save(quote);
                    }
                }
            } else {
                System.out.println("Invalid BID or ISIN");
            }
        } catch (ValidationErrorException e){
            System.out.println(e);
        }
        return new Quote();
    }

    // Удаляет запись из текущей таблицы котировок и ставит у дублированной записи в истории пометку о неактуальности
    public void delete(String isin) {
        Quote quote = quoteService.getRepository().findByIsin(isin).orElseThrow(NoEntityException::new);
        QuoteHistory qh = quoteHistoryService.getRepository().findByIsinHistWithTrue(quote.getIsin());
        //Из текущей таблицы удаляем
        quoteService.getRepository().delete(quote);
        //Из исторической меняем актуальность
        qh.setActual(false);
        quoteHistoryService.save(qh);
    }
}
