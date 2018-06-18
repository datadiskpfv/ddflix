package uk.co.datadisk.ddflix.services.film;

import org.springframework.web.multipart.MultipartFile;
import uk.co.datadisk.ddflix.dto.models.film.FilmFormDTO;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.Genre;

import java.util.List;
import java.util.Set;

public interface FilmService {

    // CRUD methods (create, read, update and delete)
    void createFilm(FilmFormDTO filmFormDTO);
    void saveFilm(Film film);

    FilmFormDTO findFilm(Long id);
    List<Film> findAll();
    Film getOne(Long id);

    List<FilmFormDTO> latestFilms();
    List<FilmFormDTO> oldestFilms();
    List<FilmFormDTO> latestGenreFilms(Genre genre);

    //Genre saveGenre(Genre Genre);

    void deleteFilmById(Long id);

    void uploader(MultipartFile filmFile);
}