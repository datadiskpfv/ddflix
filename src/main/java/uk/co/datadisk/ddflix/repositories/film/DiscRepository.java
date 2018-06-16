package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.film.Disc;
import uk.co.datadisk.ddflix.entities.film.Film;

import java.util.List;

public interface DiscRepository extends JpaRepository<Disc, Long> {

    List<Disc> findAvailableDiscsByFilmAndInStockTrue(Film film);

    List<Disc> findAvailableDiscsByFilmAndInStockTrueAAndDiscFormat(Film film, String format);
}
