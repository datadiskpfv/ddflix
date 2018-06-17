package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.film.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    Language findByLanguage(String language);
}
