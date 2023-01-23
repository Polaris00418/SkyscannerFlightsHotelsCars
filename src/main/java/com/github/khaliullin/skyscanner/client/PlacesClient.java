package com.github.khaliullin.skyscanner.client;

import com.github.khaliullin.skyscanner.dto.PlacesDto;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.List;

public interface PlacesClient {

    List<PlacesDto> retrieveListPlaces(String query, String country, String currency, String locale) throws IOException, UnirestException;
}
