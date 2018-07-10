package uk.co.datadisk.ddflix.repositories.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uk.co.datadisk.ddflix.entities.user.Country;

@Component
public class StringToCountry implements Converter<String, Country> {

    @Autowired
    CountryRepository countryRepository;

    public Country convert(String source) {
        return countryRepository.findByName(source);
    }
}
