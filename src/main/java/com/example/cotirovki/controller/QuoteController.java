package com.example.cotirovki.controller;

import com.example.cotirovki.dto.QuoteDTO;
import com.example.cotirovki.exceptions.NoEntityException;
import com.example.cotirovki.model.Quote;
import com.example.cotirovki.service.QuoteService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class QuoteController {
    private final QuoteService service;

    public QuoteController(QuoteService service) {
        this.service = service;
    }

    @GetMapping(value = "/quotes")
    public ResponseEntity<List<Quote>> findAllProf() {
        List<Quote> professorList = service.findAll();

        return professorList != null && !professorList.isEmpty()
                ? new ResponseEntity<>(professorList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/quotes")
    public ResponseEntity<?> createQuote(@RequestBody Quote quote) {
        service.save(quote);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/quotes/{isin}")
    public ResponseEntity<QuoteDTO> getQuote(@PathVariable(name = "isin") String isin) {
        QuoteDTO quoteDto = service.findByIsin(isin);

        return quoteDto != null
                ? new ResponseEntity<>(quoteDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
