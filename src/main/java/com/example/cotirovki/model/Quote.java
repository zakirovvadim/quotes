package com.example.cotirovki.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "isin")
    private String isin;

    @Column(name = "BID")
    private BigDecimal bid;

    @Column(name = "ASK")
    private BigDecimal ask;

    @Column(name = "elvl")
    private BigDecimal elvl;

    public Quote(String isin, BigDecimal bid, BigDecimal ask) {
        this.isin = isin;
        this.bid = bid;
        this.ask = ask;
        if(this.elvl == null || (bid.compareTo(this.elvl) == 1)) {
            this.elvl = bid;
        }
        if(bid == null || (ask.compareTo(this.elvl) == -1)) {
            this.elvl = ask;
        }
    }

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
        return "Quote{" +
                "id=" + id +
                ", isin='" + isin + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", elvl=" + elvl +
                '}';
    }
}
