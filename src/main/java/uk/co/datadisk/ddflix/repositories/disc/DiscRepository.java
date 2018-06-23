package uk.co.datadisk.ddflix.repositories.disc;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.disc.Disc;
import uk.co.datadisk.ddflix.entities.film.Film;

import java.util.List;

public interface DiscRepository extends JpaRepository<Disc, Long> {

    // Available discs
    List<Disc> findAvailableDiscsByFilmAndInStockTrue(Film film);

    // Discs at users uomes
    List<Disc> findAvailableDiscsByFilmAndInStockFalse(Film film);

    // Available specific format discs
    List<Disc> findAvailableDiscsByFilmAndInStockTrueAndFaultyFalseAndLostFalseAndDiscFormat(Film film, String format);

    // Specific format discs at users homes
    List<Disc> findAvailableDiscsByFilmAndInStockFalseAndDiscFormat(Film film, String format);
}