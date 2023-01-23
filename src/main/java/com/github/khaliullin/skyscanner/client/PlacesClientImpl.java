package com.github.khaliullin.skyscanner.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.khaliullin.skyscanner.dto.PlacesDto;
import com.github.khaliullin.skyscanner.service.UniRestService;
import com.github.khaliullin.skyscanner.service.UniRestServiceImpl;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PlacesClientImpl implements PlacesClient{


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
    public List<PlacesDto> retrieveListPlaces(String query, String country, String currency, String locale) throws IOException, UnirestException {
        HttpResponse<JsonNode> response = uniRestService.get(String.format(UniRestServiceImpl.PLACES_FORMAT, country, currency, locale, query));

        if (response.getStatus() != HttpStatus.SC_OK) {
            return null;
        }

        String jsonList = response.getBody().getObject().get(UniRestServiceImpl.PLACES_KEY).toString();


        return objectMapper.readValue(jsonList, new TypeReference<List<PlacesDto>>() {
        });
    }
}
