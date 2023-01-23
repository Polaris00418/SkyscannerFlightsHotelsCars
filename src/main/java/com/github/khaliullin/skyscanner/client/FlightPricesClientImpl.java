package com.github.khaliullin.skyscanner.client;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.khaliullin.skyscanner.dto.CurrencyDto;
import com.github.khaliullin.skyscanner.dto.browseflight.CarrierDto;
import com.github.khaliullin.skyscanner.dto.browseflight.FlightPricesDto;
import com.github.khaliullin.skyscanner.dto.browseflight.QuoteDto;
import com.github.khaliullin.skyscanner.exception.FlightClientException;
import com.github.khaliullin.skyscanner.service.UniRestService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FlightPricesClientImpl implements FlightPricesClient{

    public static final String BROWSE_QUOTES_FORMAT = "/apiservices/browsequotes/v1.0/%s/%s/%s/%s/%s/%s";
    public static final String OPTIONAL_BROWSE_QUOTES_FORMAT = BROWSE_QUOTES_FORMAT + "?inboundpartialdate=%s";

    public static final String QUOTES_KEY = "Quotes";
    public static final String ROUTES_KEY = "Routes";
    public static final String DATES_KEY = "Dates";
    public static final String CARRIERS_KEY = "Carriers";
    public static final String CURRENCIES_KEY = "Currencies";
    public static final String VALIDATIONS_KEY = "ValidationErrors";

    private UniRestService uniRestService;

    private ObjectMapper objectMapper;

    @Autowired
    public void setUniRestService(UniRestService uniRestService) {
        this.uniRestService = uniRestService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public FlightPricesDto browseQuotes(String country, String currency, String locale, String originalPlace, String destinationPlace, String outboundPartialDate) {
        HttpResponse<JsonNode> response = uniRestService.get(String.format(BROWSE_QUOTES_FORMAT, country, currency, locale, originalPlace, destinationPlace,
                outboundPartialDate));
        return mapToObject(response);
    }

    @Override
    public FlightPricesDto browseQuotes(String country, String currency, String locale, String originalPlace, String destinationPlace, String outboundPartialDate, String inboundPartialDate) {
        HttpResponse<JsonNode> response = uniRestService.get(String.format(OPTIONAL_BROWSE_QUOTES_FORMAT,country, currency, locale, originalPlace, destinationPlace,
                outboundPartialDate, inboundPartialDate));
        return mapToObject(response);
    }

    private FlightPricesDto mapToObject(HttpResponse<JsonNode> response) {
        if(response.getStatus() != HttpStatus.SC_OK){
                FlightPricesDto flightPricesDto = new FlightPricesDto();
                flightPricesDto.setQuotas(readValue(response.getBody().getObject().get(QUOTES_KEY).toString(), new TypeReference<List<QuoteDto>>() {
                }));
                flightPricesDto.setCarriers(readValue(response.getBody().getObject().get(CARRIERS_KEY).toString(), new TypeReference<List<CarrierDto>>() {
                }));
                flightPricesDto.setQuotas(readValue(response.getBody().getObject().get(QUOTES_KEY).toString(), new TypeReference<List<QuoteDto>>() {
                }));
                flightPricesDto.setCurrencies(readValue(response.getBody().getObject().get(CURRENCIES_KEY).toString(), new TypeReference<List<CurrencyDto>>() {
                }));
                return flightPricesDto;
        }throw new FlightClientException(String.format("There are validation errors. status code %s", response.getStatus()));
    }

    private <T> List<T> readValue(String resultAsString, TypeReference<List<T>> typeReference) {
        List<T> list;
        try{
            list = objectMapper.readValue(resultAsString, typeReference);
        }catch (IOException e){
            throw new FlightClientException("Object mapping failure");
        }
        return list;
    }


}
