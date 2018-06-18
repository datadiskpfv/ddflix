package uk.co.datadisk.ddflix.dto.mapper.film;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.ddflix.dto.models.film.FilmFormDTO;
import uk.co.datadisk.ddflix.entities.film.Film;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface FilmFormMapper {

    // INSTANCE is used by the tests
    FilmFormMapper INSTANCE = Mappers.getMapper(FilmFormMapper.class);

    // Film -> FilmFormDTO (the mappings is for the conversion from String to Date format - MySQL format)
    @Mapping(target="dateCreated", source="dateCreated", dateFormat="yyyy-MM-dd HH:mm:ss")
    FilmFormDTO FilmToFilmFormDTO(Film film);

    // FilmFormDTO -> Film (the mappings is for the conversion from String to Date format - MySQL format)
    @Mapping(source = "dateCreated", target = "dateCreated", dateFormat="yyyy-MM-dd HH:mm:ss")
    Film FilmFormDTOToFilm(FilmFormDTO filmFormDTO);

    // No ordering on SETS
    Set<FilmFormDTO> FilmsToFilmFormsDTO(Set<Film> films);

    // Ordering will be determined by the SQL query (repository)
    List<FilmFormDTO> FilmsToFilmFormsDTO(List<Film> films);
}