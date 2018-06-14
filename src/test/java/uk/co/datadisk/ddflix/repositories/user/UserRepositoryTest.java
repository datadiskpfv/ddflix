package uk.co.datadisk.ddflix.repositories.user;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.ddflix.DdflixApplication;
import uk.co.datadisk.ddflix.entities.user.Address;
import uk.co.datadisk.ddflix.entities.user.User;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class UserRepositoryTest {

    //private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Before
    public void setUp() {
        User user1 = User.builder()
                .email("paul.valle@example.com")
                .password("password")
                .username("Paul_1")
                .build();

        Address address = addressRepository.findById(8L).get();

        user1.setDefaultShippingAddress(address);
        user1.setDefaultBillingAddress(address);

        userRepository.save(user1);
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void checkUserData() {
        User user1 = userRepository.findByEmail("paul.valle@example.com");
        assertEquals("Guernsey", user1.getDefaultShippingAddress().getCity().getCountry().getName());

        User user2 = userRepository.findByEmail("will.hay@example.com");
        assertEquals(4, user2.getShippingAddresses().size());
    }

}