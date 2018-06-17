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
public class ReviewRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    ReviewRepository ReviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FilmRepository filmRepository;

    @Test
    @Transactional
    //@Rollback(false)
    public void createReview(){
        User user = userRepository.findByEmail("graham.moffatt@example.com");
        Film film = filmRepository.findByTitle("Star Wars");
        user.addReview(film, "Still the best sci-fi film ever");
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void deleteReview(){
        User user = userRepository.findByEmail("graham.moffatt@example.com");
        Film film = filmRepository.findByTitle("Alien");
        user.removeReview(film);
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void updateReview(){
        User user = userRepository.findByEmail("graham.moffatt@example.com");
        Film film = filmRepository.findByTitle("Alien");
        assertEquals("This is a must watch film, good and scary", ReviewRepository.findByUserAndFilm(user, film).getReview());

        if(user.checkReview(film)) {
            user.removeReview(film);
            em.flush();
            em.refresh(user);
        }
        user.addReview(film, "Film review updated!!!!");
        assertNotNull(ReviewRepository.findByUserAndFilm(user, film));
        assertEquals("Film review updated!!!!", ReviewRepository.findByUserAndFilm(user, film).getReview());
    }

}