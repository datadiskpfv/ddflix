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
import uk.co.datadisk.ddflix.entities.film.ActorImage;
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

    @Autowired
    ActorImageRepository actorImageRepository;

    @Test
    @Transactional
    public void checkActor() {
        Actor actor = actorRepository.findByFirstNameAndLastName("Tom", "Skerritt");
        assertEquals("Skerritt", actor.getLastName());
        assertEquals("United States of America", actor.getBirthCountry().getName());
    }

    @Test
    @Transactional
    //@Rollback(false)
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
    //@Rollback(false)
    public void deleteActor() {
        Actor actor1 = actorRepository.findByFirstNameAndLastName("Veronica", "Cartwright");
        assertNotNull(actor1);

        // We need to delete the actor from all films
        for(Film film : actor1.getFilms()){
            System.out.println("Film: " + film.getTitle());
            film.removeActor(actor1);
            System.out.println("Film :" + film.getActors());
        }
        actorRepository.delete(actor1);

        Actor actor2 = actorRepository.findByFirstNameAndLastName("Veronica", "Cartwright");
        assertNull(actor2);
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void updateActor() {
        Country uk = countryRepository.findByName("United Kingdom");
        Actor actor1 = actorRepository.findByFirstNameAndLastName("Yaphet", "Kotto");
        actor1.setBirthCountry(uk);

        Actor actor2 = actorRepository.findByFirstNameAndLastName("Yaphet", "Kotto");
        assertEquals("United Kingdom", actor2.getBirthCountry().getName());
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void addActorImage() {
        Actor actor = actorRepository.findByFirstNameAndLastName("Veronica", "Cartwright");

        ActorImage actorImageA = new ActorImage();
        actorImageA.setImageName("VeronicaA");

        ActorImage actorImageB = new ActorImage();
        actorImageB.setImageName("VeronicaB");

        actor.addActorImage(actorImageA);
        actor.addActorImage(actorImageB);

        Actor actor2 = actorRepository.findByFirstNameAndLastName("Veronica", "Cartwright");
        assertEquals(2, actor2.getActorImages().size());
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void removeActorImage() {
        Actor actor = actorRepository.findByFirstNameAndLastName("Sigourney", "Weaver");
        ActorImage actorImage = actorImageRepository.findById(3L).get();

        assertEquals(2, actor.getActorImages().size());

        actor.removeActorImage(actorImage);
        assertEquals(1, actor.getActorImages().size());
    }
}