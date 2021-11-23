package ru.vadim.quotes.controller;

import ru.vadim.quotes.dto.QuoteDTO;
import ru.vadim.quotes.model.Quote;
import ru.vadim.quotes.service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {
    private final QuoteService service;

    public QuoteController(QuoteService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<QuoteDTO>> findAllQuotes() {
        List<QuoteDTO> quoteList = service.findAll();
        return quoteList != null
                ? new ResponseEntity<>(quoteList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createQuote(@RequestBody Quote quote) {
        service.save(quote);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{isin}")
    public ResponseEntity<QuoteDTO> getQuote(@PathVariable(name = "isin") String isin) {
        QuoteDTO quoteDto = service.findByIsin(isin);

        return quoteDto != null
                ? new ResponseEntity<>(quoteDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
