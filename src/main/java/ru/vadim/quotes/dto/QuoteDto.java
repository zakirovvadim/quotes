package ru.vadim.quotes.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class QuoteDto {

    @ApiModelProperty(name = "Идентификатор", example = "1")
    private Integer id;
    @ApiModelProperty(name = "Международный идентификационный код ценной бумаги", example = "RU000A0JX0K9")
    private String isin;
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal elvl;
    private boolean isActual;
}
