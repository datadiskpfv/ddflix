package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.Genre;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

  Film findByTitle(String title);

  // oldest films
  List<Film> findFirst20ByOrderByReleaseDateAsc();

  // newest films
  List<Film> findFirst20ByOrderByReleaseDateDesc();

  // latest films by Genre
  List<Film> findFirst20ByGenresOrderByReleaseDateDesc(Genre genre);

}