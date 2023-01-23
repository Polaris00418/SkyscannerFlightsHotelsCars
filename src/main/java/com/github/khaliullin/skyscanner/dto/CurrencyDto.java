package com.github.khaliullin.skyscanner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrencyDto {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Symbol")
    private String symbol;

    @JsonProperty("ThousandsSeparator")
    private String thousandsSeparator;

    @JsonProperty("DecimalSeparator")
    private String decimalSeparator;

    @JsonProperty("SymbolOnLeft")
    private boolean symbolOnLeft;

    @JsonProperty("SpacesBetweenAmountAndSymbol")
    private boolean spaceBetweenAmountAndSymbol;

    @JsonProperty("RoundingCoefficient")
    private int roundingCoefficient;

    @JsonProperty("DecimalDigits")
    private int decimalDigits;

}
