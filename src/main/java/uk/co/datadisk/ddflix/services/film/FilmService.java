package uk.co.datadisk.ddflix.services.film;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import uk.co.datadisk.ddflix.dto.models.film.FilmFormDTO;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.Genre;

import java.util.List;

public interface FilmService {

    // CRUD methods (create, read, update and delete)
    void createFilm(FilmFormDTO filmFormDTO);
    void saveFilm(Film film);

    FilmFormDTO findFilmDTO(Long id);
    List<Film> findAll();
    Page<Film> findAll(Pageable page);
    Page<Film> findAllByGenre(String genre, Pageable page);
    Page<Film> findFilmOptions(String action, Pageable pageable);
    Page<Film> FindFilmBySearchString(String searchString, Pageable pageable);
    Film findFilm(Long id);

    List<FilmFormDTO> latestFilms();
    List<FilmFormDTO> oldestFilms();
    List<FilmFormDTO> latestGenreFilms(Genre genre);

    void deleteFilmById(Long id);

    void uploader(MultipartFile filmFile);


}