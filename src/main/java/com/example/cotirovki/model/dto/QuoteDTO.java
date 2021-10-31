package com.example.cotirovki.model.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

public class QuoteDTO {
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name = "isin")
    private String isin;

    @Column(name = "bid")
    private BigDecimal bid;

    @Column(name = "ask")
    private BigDecimal ask;

    @Column(name = "elvl")
    private BigDecimal elvl;

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
