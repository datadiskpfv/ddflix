package uk.co.datadisk.ddflix.repositories.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.ddflix.DdflixApplication;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.repositories.user.UserRepository;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class RatingRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FilmRepository filmRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void createRating(){
      User user = userRepository.findByEmail("graham.moffatt@example.com");
      Film film = filmRepository.findByTitle("Star Wars");
      user.addRating(film, 5);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteRating(){
        User user = userRepository.findByEmail("graham.moffatt@example.com");
        Film film = filmRepository.findByTitle("Alien");
        user.removeRating(film);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateRating(){
        User user = userRepository.findByEmail("graham.moffatt@example.com");
        Film film = filmRepository.findByTitle("Alien");
        assertEquals(5, ratingRepository.findByUserAndFilm(user, film).getRating().longValue());

        if(user.checkRating(film)) {
          user.removeRating(film);
          em.flush();
          em.refresh(user);
        }
        user.addRating(film, 1);
        assertNotNull(ratingRepository.findByUserAndFilm(user, film));
        assertEquals(1, ratingRepository.findByUserAndFilm(user, film).getRating().longValue());
    }
}