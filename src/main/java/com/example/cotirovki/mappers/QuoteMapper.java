package com.example.cotirovki.mappers;

import com.example.cotirovki.dto.QuoteDTO;
import com.example.cotirovki.model.Quote;



public interface QuoteMapper {
    Quote toQuote(QuoteDTO dtoQuote);

    QuoteDTO toDto(Quote quote);
}
