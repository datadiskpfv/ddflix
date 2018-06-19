package uk.co.datadisk.ddflix.services.impl;

import org.springframework.stereotype.Service;
import uk.co.datadisk.ddflix.entities.user.City;
import uk.co.datadisk.ddflix.repositories.user.CityRepository;
import uk.co.datadisk.ddflix.services.CityService;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAllCities() {
        return cityRepository.findAllByOrderByCityAsc();
    }
}