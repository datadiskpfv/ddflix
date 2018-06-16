package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.film.Disc;

public interface DiscRepository extends JpaRepository<Disc, Long> {
}
