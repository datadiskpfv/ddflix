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
import uk.co.datadisk.ddflix.entities.film.Genre;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class GenreRepositoryTest {
    
    @Autowired
    GenreRepository genreRepository;

    @Test
    @Transactional
    public void checkGenre() {
        Genre horror = genreRepository.findByName("Horror");
        assertNotNull(horror);
        assertEquals("Horror", horror.getName());
    }

    @Test
    @Transactional
    public void createGenre() {
        Genre genre = new Genre();
        genre.setName("TVSeries");
        genreRepository.save(genre);

        Genre g1 = genreRepository.findByName("TVSeries");
        assertNotNull(g1);
        assertEquals("TVSeries", g1.getName());
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void deleteGenre() {
       Genre junk = genreRepository.findByName("Junk");
       assertNotNull(junk);
       genreRepository.delete(junk);

       assertNull(genreRepository.findByName("Junk"));
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void updateGenre() {
        Genre junk = genreRepository.findByName("Junk");
        junk.setName("Junk - updated");
        genreRepository.save(junk);

        Genre g1 = genreRepository.findByName("Junk - updated");
        assertNotNull(g1);
        assertEquals("Junk - updated", g1.getName());
    }
}