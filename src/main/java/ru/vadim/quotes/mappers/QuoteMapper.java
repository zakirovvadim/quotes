package ru.vadim.quotes.mappers;

import ru.vadim.quotes.dto.QuoteDto;
import ru.vadim.quotes.model.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface QuoteMapper {
    QuoteMapper INSTANCE = Mappers.getMapper(QuoteMapper.class);

    List<QuoteDto> map(List<Quote> sourse);
    Quote map(QuoteDto sourse);
    QuoteDto map(Quote soure);
}
