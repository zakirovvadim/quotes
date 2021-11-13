package com.example.cotirovki.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class QuoteDTO {

    private Integer id;
    private String isin;
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal elvl;
    private boolean isActual;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getElvl() {
        return elvl;
    }

    public void setElvl(BigDecimal elvl) {
        this.elvl = elvl;
    }

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
