package uk.co.datadisk.ddflix.repositories.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.ddflix.DdflixApplication;
import uk.co.datadisk.ddflix.entities.user.Country;
import uk.co.datadisk.ddflix.entities.user.User;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    @Transactional
    //@Rollback(false)
    public void checkUserData() {
        Country uk = countryRepository.findByName("United Kingdom");
        assertEquals(7, uk.getCities().size());

        // I am expecting one address per city with the test data
        assertEquals(1, uk.getCities().iterator().next().getAddresses().size());
    }

}