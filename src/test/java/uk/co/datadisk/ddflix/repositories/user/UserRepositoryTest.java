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
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.user.Address;
import uk.co.datadisk.ddflix.entities.user.Role;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.repositories.film.FilmRepository;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class UserRepositoryTest {

    //private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    FilmRepository filmRepository;

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

    @Test
    @Transactional
    public void addRole() {
        Role role = roleRepository.findByName("ADMIN");
        User user = userRepository.findByEmail("paul.valle@example.com");

        assertEquals(0, user.getRoles().size());
        user.addRole(role);
        userRepository.save(user);

        User user2 = userRepository.findByEmail("paul.valle@example.com");
        assertEquals(1, user2.getRoles().size());
        assertTrue(user2.getRoles().contains(role));
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void deleteRole() {
        Role role = roleRepository.findByName("ADMIN");
        User user = userRepository.findByEmail("will.hay@example.com");

        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains(role));

        user.removeRole(role);
        userRepository.save(user);

        User user2 = userRepository.findByEmail("will.hay@example.com");
        assertEquals(1, user2.getRoles().size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void addFilmToWishlist() {
        User user = userRepository.findByEmail("paul.valle@example.com");
        Film film1 = filmRepository.findByTitle("Alien");
        Film film2 = filmRepository.findByTitle("Harry Potter");

        assertEquals(0, user.getWishlists().size());

        user.addFilmToWishList(film1);

        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        user.addFilmToWishList(film2);
        userRepository.save(user);
        assertEquals(2, user.getWishlists().size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void removeFilmFromWishlist() {
        User user = userRepository.findByEmail("graham.moffatt@example.com");
        Film film1 = filmRepository.findByTitle("Alien");

        assertEquals(2, user.getWishlists().size());

        user.removeFilmFromWishlist(film1);
        assertEquals(1, user.getWishlists().size());
    }
}