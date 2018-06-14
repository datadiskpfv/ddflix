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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CityRepository cityRepository;

    @Before
    public void setUp() throws Exception {
        City london = cityRepository.findByCity("London");

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
    @Rollback(false)
    public void checkAddress() {
        Address address = addressRepository.findById(10L).get();
        assertEquals("221B Baker Street", address.getLine1());
    }
}