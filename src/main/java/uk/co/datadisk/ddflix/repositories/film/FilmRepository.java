package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.Genre;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

  Film findByTitle(String title);

  // oldest films
  List<Film> findFirst20ByOrderByReleaseDateAsc();
  Page<Film> findFirst20ByOrderByReleaseDateAsc(Pageable pageable);

  // newest films
  List<Film> findFirst20ByOrderByReleaseDateDesc();
  Page<Film> findFirst20ByOrderByReleaseDateDesc(Pageable pageable);

  // latest films by Genre
  List<Film> findFirst20ByGenresOrderByReleaseDateDesc(Genre genre);

  Page<Film> findByGenres(Genre genre, Pageable page);

  Page<Film> findDistinctByTitleContainingOrGenresNameContaining(String title, String name, Pageable page);
}