package com.example.cotirovki.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
@Builder
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quote_history")
public class QuoteHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QuoteHistory that = (QuoteHistory) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
