package com.example.cotirovki.model;

import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quote")
@Data
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "isin")
    private String isin;

    @Column(name = "bid")
    private BigDecimal bid;

    @Column(name = "ask")
    private BigDecimal ask;

    @Column(name = "elvl")
    private BigDecimal elvl;

    @Column(name = "is_actual")
    private boolean isActual;

    public Quote(String isin, BigDecimal bid, BigDecimal ask) {
        this.isin = isin;
        this.bid = bid;
        this.ask = ask;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }
}
