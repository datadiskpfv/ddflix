package uk.co.datadisk.ddflix.services.film;

import uk.co.datadisk.ddflix.dto.models.film.ClassificationFormDTO;
import uk.co.datadisk.ddflix.entities.film.Classification;

import java.util.List;

public interface ClassificationService {

    // CRUD methods (create, read, update and delete)
    void createClassification(ClassificationFormDTO classificationFormDTO);

    ClassificationFormDTO findClassification(Long id);
    List<Classification> findAll();
    Classification findByRating(String rating);

    //Genre saveGenre(Genre Genre);

    void deleteClassificationById(Long id);
}
