package uk.co.datadisk.ddflix.repositories.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.ddflix.DdflixApplication;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.FilmsAtHome;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.repositories.user.UserRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class DiscRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DiscRepository discRepository;

    @Autowired
    FilmRepository filmRepository;

    @Test
    @Transactional
    public void userFilmsAtHome(){
        User user1 = userRepository.findByEmail("graham.moffatt@example.com");
        assertEquals(3, user1.getFilmsAtHomes().size());

        User user2 = userRepository.findByEmail("moore.marriott@example.com");
        assertEquals(2, user2.getFilmsAtHomes().size());

    }

    @Test
    @Transactional
    public void filmsAtHome(){
        Film film1 = filmRepository.findById(1L).get();
        System.out.println(film1.getDiscs());
    }

}