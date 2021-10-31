package com.example.cotirovki.controller;

import com.example.cotirovki.model.Quote;
import com.example.cotirovki.service.QuoteAndHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class Controller {
    private final QuoteAndHistoryService service;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Quote>> findAllProf() {
        List<Quote> professorList = service.findAll();

        return professorList != null && !professorList.isEmpty()
                ? new ResponseEntity<>(professorList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/createQuote")
    public ResponseEntity<?> createProf(@RequestBody Quote quote) {
        service.save(quote);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/getByIsisn/{isin}")
    public ResponseEntity<Quote> readProf(@PathVariable(name = "isin") String isin) {
        Quote quote = service.findByIsin(isin);
        return quote != null
                ? new ResponseEntity<>(quote, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
