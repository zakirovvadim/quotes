package ru.vadim.quotes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class QuoteDTO {

    private Integer id;
    private String isin;
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal elvl;
    private boolean isActual;

    @Override
    public String toString() {
        return "QuoteDTO{" +
                "isin='" + isin + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", elvl=" + elvl +
                '}';
    }
}
