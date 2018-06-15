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
import uk.co.datadisk.ddflix.entities.film.Classification;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.Genre;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class FilmRepositoryTest {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    ClassificationRepository classificationRepository;

    @Autowired
    GenreRepository genreRepository;

    @Test
    @Transactional
    public void checkFilm() {
        Film alien = filmRepository.findByTitle("Alien");
        assertNotNull(alien);
        assertEquals("Alien", alien.getTitle());
        assertEquals("18", alien.getClassification().getRating());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void createFilm() {
        Classification classification = classificationRepository.findByRating("18");
        Genre genre = genreRepository.findByName("Horror");

        Film film = new Film();
        film.setTitle("Aliens");
        film.setClassification(classification);
        film.setDescription("Sci-Fi horror movie");
        film.setReleaseDate(new GregorianCalendar(1986, Calendar.AUGUST, 26).getTime());
        film.setCoverImage("aliens.jpg");
        film.setStatus(true);
        film.addGenre(genre);

        filmRepository.save(film);

        Film f1 = filmRepository.findByTitle("Aliens");
        assertNotNull(f1);
        assertEquals("18", f1.getClassification().getRating());
        assertTrue(f1.getGenres().contains(genre));
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void deleteFilm() {
        Film rambo = filmRepository.findByTitle("Rambo");
        assertNotNull(rambo);
        filmRepository.delete(rambo);

        assertNull(filmRepository.findByTitle("Rambo"));
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void updateFilm() {
        Film rambo = filmRepository.findByTitle("Rambo");
        rambo.setTitle("Rambo - updated");
        filmRepository.save(rambo);

        Film f1 = filmRepository.findByTitle("Rambo - updated");
        assertNotNull(f1);
        assertEquals("Rambo - updated", f1.getTitle());
    }

}