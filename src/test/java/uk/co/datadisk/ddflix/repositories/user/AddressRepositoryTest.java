package uk.co.datadisk.ddflix.repositories.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.ddflix.DdflixApplication;
import uk.co.datadisk.ddflix.entities.user.Address;
import uk.co.datadisk.ddflix.entities.user.City;
import uk.co.datadisk.ddflix.entities.user.Country;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    @Before
    public void setUp() throws Exception {
        City london = cityRepository.findByCity("London");

        // use existing city/country
        Address address = Address.builder()
                .line1("221B Baker Street")
                .line2("Marylebone")
                .city(london)
                .postcode("NW1 6XE")
                .build();
        addressRepository.save(address);
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void checkAddress() {
        Address address = addressRepository.findByPostcode("NW1 6XE");
        assertEquals("221B Baker Street", address.getLine1());
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void createFullAddressIncludingCityAndCountry() {

        // create new city and Country
        Country usa = new Country();
        usa.setCountry("US");
        usa.setName("United States Of America");
        usa.setIso("US");
        usa.setTld(".us");

        City new_york = new City();
        new_york.setCountry(usa);
        new_york.setCity("New York");

        Address empire_state_building = Address.builder()
                .line1("Empire State Building")
                .line2("350")
                .line3("5th Ave")
                .city(new_york)
                .postcode("NY 10118")
                .build();

        addressRepository.save(empire_state_building);

        Address address = addressRepository.findByPostcode("NY 10118");
        assertEquals("Empire State Building", address.getLine1());
    }

    @Test
    @Transactional
    public void deleteAddress(){
        Address address = addressRepository.findById(9L).get();
        assertEquals("DU126MY", address.getPostcode());
        addressRepository.delete(address);
        assertFalse(addressRepository.findById(9L).isPresent());
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void updateAddress(){
        Address address = addressRepository.findById(9L).get();
        address.setLine1("Dummy 6 - updated");
        addressRepository.save(address);

        address = addressRepository.findById(9L).get();
        assertEquals("Dummy 6 - updated", address.getLine1());
        assertNotEquals("Dummy 6", address.getLine1());
    }
}