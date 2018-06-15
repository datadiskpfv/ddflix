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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class ClassificationRepositoryTest {

    @Autowired
    ClassificationRepository classificationRepository;

    @Test
    @Transactional
    public void checkClassification() {
        Classification classification = classificationRepository.findByRating("PG");
        assertEquals("PG.png", classification.getImage_name());
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void createClassification() {
        Classification classification = new Classification();
        classification.setRating("21");
        classification.setImage_name("21.png");
        classificationRepository.save(classification);

        Classification cl1 = classificationRepository.findByRating("21");
        assertEquals("21.png", cl1.getImage_name());
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void deleteClassification() {
        Classification classification = classificationRepository.findByRating("99");
        assertNotNull(classification.getImage_name());

        classificationRepository.delete(classification);

        Classification cl1 = classificationRepository.findByRating("99");
        assertNull(cl1);
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void updateClassification() {
        Classification classification = classificationRepository.findByRating("99");
        classification.setRating("99 - updated");
        classificationRepository.save(classification);

        assertEquals("99 - updated", classificationRepository.findByRating("99 - updated").toString());
    }

}