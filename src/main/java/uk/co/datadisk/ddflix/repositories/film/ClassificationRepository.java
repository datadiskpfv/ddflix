package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.film.Classification;

public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Classification findByRating(String rating);
}