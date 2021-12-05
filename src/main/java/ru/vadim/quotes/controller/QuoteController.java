package ru.vadim.quotes.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import ru.vadim.quotes.dto.QuoteDto;
import ru.vadim.quotes.model.Quote;
import ru.vadim.quotes.service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService service;

    @GetMapping(value = "")
    public ResponseEntity<List<QuoteDto>> findAllQuotes() {
        List<QuoteDto> quoteList = service.findAll();
        return quoteList != null
                ? new ResponseEntity<>(quoteList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createQuote(@RequestBody Quote quote) {
        service.save(quote);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "Получение списка всех котировок",
            responses = {@ApiResponse(responseCode = "200", description = "Получение котировки"),
                    @ApiResponse(responseCode = "404", description = "Котировка не найдена")})
    @GetMapping(value = "/{isin}")
    public ResponseEntity<QuoteDto> getQuote(
            @PathVariable(name = "isin")
            @Parameter(description = "Международный идентификационный код ценной бумаги")
                    String isin) {
        QuoteDto quoteDto = service.findByIsin(isin);

        return quoteDto != null
                ? new ResponseEntity<>(quoteDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
