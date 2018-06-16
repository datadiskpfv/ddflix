package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.film.ActorImage;

public interface ActorImageRepository extends JpaRepository<ActorImage, Long> {
}