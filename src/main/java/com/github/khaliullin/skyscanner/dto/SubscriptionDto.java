package com.github.khaliullin.skyscanner.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.khaliullin.skyscanner.dto.browseflight.FlightPricesDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SubscriptionDto {

    private Long id;

    @NotNull
    @Email
    @ApiModelProperty(value = "Subscriber's email", example = "test@test.com")
    private String email;

    @NotNull
    @ApiModelProperty(value = "Country Code", example = "UA")
    private String country;

    @NotNull
    @ApiModelProperty(value = "Currency Code", example = "UAH")
    private String currency;

    @NotNull
    @ApiModelProperty(value = "Locale", example = "ru-RU")
    private String locale;

    @NotNull
    @ApiModelProperty(value = "Code of the origin place", example = "HRK-sky")
    private String originPlace;

    @NotNull
    @ApiModelProperty(value = "Code of the destination place", example = "KBP-sky")
    private String destinationPlace;

    @NotNull
    @ApiModelProperty(value = "Date front", example = "2019-07-13")@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate outboundPartialDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Date back", example = "2019-07-18")
    private LocalDate inboundPartialDate;

    private Integer minPrice;

    private FlightPricesDto flightPricesDto;

}
