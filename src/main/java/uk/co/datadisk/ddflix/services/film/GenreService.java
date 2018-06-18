package uk.co.datadisk.ddflix.services.film;

import uk.co.datadisk.ddflix.dto.models.film.GenreFormDTO;
import uk.co.datadisk.ddflix.entities.film.Genre;

import java.util.List;

public interface GenreService {

    // CRUD methods (create, read, update and delete)
    void createGenre(GenreFormDTO genreFormDTO);

    GenreFormDTO findGenre(Long id);
    Genre findByName(String name);
    List<Genre> findAll();

    //Genre saveGenre(Genre Genre);

    void deleteGenreById(Long id);
}
