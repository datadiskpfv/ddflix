package uk.co.datadisk.ddflix.repositories.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.datadisk.ddflix.DdflixApplication;
import uk.co.datadisk.ddflix.entities.film.Language;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class LanguageRepositoryTest {

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    FilmRepository filmRepository;

    @Test
    @Transactional
    //@Rollback(false)
    public void checkLanguage(){
        Language spanish = languageRepository.findByLanguage("Spanish");
        assertNotNull(spanish);
        assertEquals("Spanish", spanish.getLanguage());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void createLanguage(){
        Language polish = new Language("Polish");
        languageRepository.save(polish);
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void deleteLanguage(){
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void updateLanguage(){
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void addFilmLanguage(){
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void removeFilmLanguage(){
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void addFilmSubtitle(){
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void removeFilmSubtitle(){
    }

}