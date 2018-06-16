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
import uk.co.datadisk.ddflix.entities.film.Actor;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.Gender;
import uk.co.datadisk.ddflix.entities.user.Country;
import uk.co.datadisk.ddflix.repositories.user.CountryRepository;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class ActorRepositoryTest {

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    FilmRepository filmRepository;

    @Test
    @Transactional
    public void checkActor() {
        Actor actor = actorRepository.findByFirstNameAndLastName("Tom", "Skerritt");
        assertEquals("Skerritt", actor.getLastName());
        assertEquals("United States of America", actor.getBirthCountry().getName());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void createActor() {
        Country usa = countryRepository.findByName("United States of America");

        Actor actor = new Actor();
        actor.setFirstName("Harry Dean");
        actor.setLastName("Stanton");
        actor.setBirthDate(new Date());
        actor.setGender(Gender.MALE);
        actor.setBirthCountry(usa);
        actorRepository.save(actor);

        assertEquals("US", actor.getBirthCountry().getCountry());
        assertEquals("Harry Dean", actor.getFirstName());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteActor() {
        Actor actor1 = actorRepository.findByFirstNameAndLastName("Veronica", "Cartwright");
        assertNotNull(actor1);

        for(Film film : actor1.getFilms()){
            System.out.println("Film: " + film.getTitle());
            film.removeActor(actor1);
            System.out.println("Film :" + film.getActors());
        }
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void updateActor() {
    }

}