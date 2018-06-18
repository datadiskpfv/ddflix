package uk.co.datadisk.ddflix.services.film.impl;

import org.springframework.stereotype.Service;
import uk.co.datadisk.ddflix.dto.mapper.film.GenreFormMapper;
import uk.co.datadisk.ddflix.dto.models.film.GenreFormDTO;
import uk.co.datadisk.ddflix.entities.film.Genre;
import uk.co.datadisk.ddflix.repositories.film.GenreRepository;
import uk.co.datadisk.ddflix.services.film.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreFormMapper genreFormMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreFormMapper genreFormMapper) {
        this.genreRepository = genreRepository;
        this.genreFormMapper = genreFormMapper;
    }

    @Override
    public void createGenre(GenreFormDTO genreFormDTO) {
        Genre genre = genreFormMapper.GenreFormDTOToGenre(genreFormDTO);
        genreRepository.saveAndFlush(genre);
    }

    @Override
    public GenreFormDTO findGenre(Long id) {
        return genreFormMapper.GenreToGenreFormDTO(genreRepository.getOne(id));
    }

    @Override
    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }
}