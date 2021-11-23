package ru.vadim.quotes.mappers;

import ru.vadim.quotes.dto.QuoteDTO;
import ru.vadim.quotes.model.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface QuoteMapper {
    QuoteMapper INSTANCE = Mappers.getMapper(QuoteMapper.class);

    List<QuoteDTO> map(List<Quote> quotes);
    Quote map(QuoteDTO dtoQuote);
    QuoteDTO map(Quote quote);
}
