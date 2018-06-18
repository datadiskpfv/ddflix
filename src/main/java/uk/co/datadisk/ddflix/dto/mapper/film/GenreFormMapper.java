package uk.co.datadisk.ddflix.dto.mapper.film;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.ddflix.dto.models.film.GenreFormDTO;
import uk.co.datadisk.ddflix.entities.film.Genre;

@Mapper(componentModel = "spring")
public interface GenreFormMapper {

    // INSTANCE is used by the tests
    GenreFormMapper INSTANCE = Mappers.getMapper(GenreFormMapper.class);

    // Genre -> GenreFormDTO (the mappings is for the conversion from String to Date format - MySQL format)
    @Mapping(target="dateCreated", source="dateCreated", dateFormat="yyyy-MM-dd HH:mm:ss")
    GenreFormDTO GenreToGenreFormDTO(Genre genre);

    // GenreFormDTO -> Genre (the mappings is for the conversion from String to Date format - MySQL format)
    @Mapping(source = "dateCreated", target = "dateCreated", dateFormat="yyy-MM-dd HH:mm:ss")
    Genre GenreFormDTOToGenre(GenreFormDTO genreFormDTO);
}