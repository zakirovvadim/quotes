//package com.example.cotirovki.utils;
//import com.example.cotirovki.dto.QuoteDTO;
//import com.example.cotirovki.model.Quote;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MappingUtils {
//    public QuoteDTO mapToDto(Quote quote) {
//        QuoteDTO dto = new QuoteDTO();
//        dto.setIsin(quote.getIsin());
//        dto.setBid(quote.getBid());
//        dto.setAsk(quote.getAsk());
//        dto.setElvl(quote.getElvl());
//        return dto;
//    }
//
//    public Quote mapToQuote(QuoteDTO dto) {
//        Quote quote = new Quote();
//        quote.setIsin(dto.getIsin());
//        quote.setBid(dto.getBid());
//        quote.setAsk(dto.getAsk());
//        quote.setElvl(dto.getElvl());
//        return quote;
//    }
//}
