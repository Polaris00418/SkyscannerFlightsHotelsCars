package com.github.khaliullin.skyscanner.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.khaliullin.skyscanner.dto.CountryDto;
import com.github.khaliullin.skyscanner.dto.CurrencyDto;
import com.github.khaliullin.skyscanner.service.UniRestService;
import com.github.khaliullin.skyscanner.service.UniRestServiceImpl;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;



@Component
public class LocalisationClientImpl implements LocalisationClient{

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
    public List<CurrencyDto> retrieveCurrencies() throws IOException {
        HttpResponse<JsonNode> response = uniRestService.get(UniRestServiceImpl.CURRENCIES_KEY);

        if(response.getStatus() != HttpStatus.SC_OK){
            return null;
        }

        String jsonList = response.getBody().getObject().get(UniRestServiceImpl.CURRENCIES_KEY).toString();

        return objectMapper.readValue(jsonList, new TypeReference<List<CurrencyDto>>() {
        });
    }

    @Override
    public List<CountryDto> retrieveCountries(String locale) throws IOException {

        HttpResponse<JsonNode> response = uniRestService.get(String.format(UniRestServiceImpl.COUNTRIES_FORMAT, locale));

        if(response.getStatus() != HttpStatus.SC_OK){
            return null;
        }

        String jsonList = response.getBody().getObject().get(UniRestServiceImpl.COUNTRIES_KEY).toString();

        return objectMapper.readValue(jsonList, new TypeReference<List<CountryDto>>() {});
    }
}
