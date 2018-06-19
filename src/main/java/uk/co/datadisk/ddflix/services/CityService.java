package uk.co.datadisk.ddflix.services;

import uk.co.datadisk.ddflix.entities.user.City;

import java.util.List;

public interface CityService {

    List<City> findAllCities();
}
