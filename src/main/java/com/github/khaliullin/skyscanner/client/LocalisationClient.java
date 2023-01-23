package com.github.khaliullin.skyscanner.client;

import com.github.khaliullin.skyscanner.dto.CountryDto;
import com.github.khaliullin.skyscanner.dto.CurrencyDto;

import java.io.IOException;
import java.util.List;

public interface LocalisationClient {


    List<CurrencyDto> retrieveCurrencies() throws IOException;

    List<CountryDto> retrieveCountries(String locale) throws IOException;


}
