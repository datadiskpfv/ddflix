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
  Page<Film> findFirst12ByOrderByReleaseDateAsc(Pageable pageable);

  // newest films
  List<Film> findFirst20ByOrderByReleaseDateDesc();
  Page<Film> findFirst12ByOrderByReleaseDateDesc(Pageable pageable);

  // latest films by Genre
  List<Film> findFirst20ByGenresOrderByReleaseDateDesc(Genre genre);

  // Find films by specific Genre
  Page<Film> findByGenresNameContaining(String genre, Pageable page);

  // Find films using a search string, looking at film title and any genres that it has
  Page<Film> findDistinctByTitleContainingOrGenresNameContaining(String title, String name, Pageable page);
}