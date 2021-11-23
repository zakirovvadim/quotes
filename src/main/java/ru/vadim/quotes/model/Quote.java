package ru.vadim.quotes.model;

import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Entity
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

}
