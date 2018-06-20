package uk.co.datadisk.ddflix.services.film.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uk.co.datadisk.ddflix.dto.mapper.film.FilmFormMapper;
import uk.co.datadisk.ddflix.dto.models.film.FilmFormDTO;
import uk.co.datadisk.ddflix.entities.film.Classification;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.Genre;
import uk.co.datadisk.ddflix.repositories.film.FilmRepository;
import uk.co.datadisk.ddflix.services.film.ClassificationService;
import uk.co.datadisk.ddflix.services.film.FilmService;
import uk.co.datadisk.ddflix.services.film.GenreService;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final FilmFormMapper filmFormMapper;
    private final GenreService genreService;
    private final ClassificationService classificationService;

    public FilmServiceImpl(FilmRepository filmRepository, FilmFormMapper filmFormMapper, GenreService genreService, ClassificationService classificationService) {
        this.filmRepository = filmRepository;
        this.filmFormMapper = filmFormMapper;
        this.genreService = genreService;
        this.classificationService = classificationService;
    }

    @Override
    public void createFilm(FilmFormDTO filmFormDTO) {
        Film film = filmFormMapper.FilmFormDTOToFilm(filmFormDTO);

        // I could change the HTML but want to play with date conversion
        //film.setReleaseDate(DateUtils.dateConvert(film.getReleaseDate()));
        filmRepository.saveAndFlush(film);
    }

    @Override
    public void saveFilm(Film film) {
        filmRepository.saveAndFlush(film);
    }

    @Override
    public FilmFormDTO findFilmDTO(Long id) {
        return filmFormMapper.FilmToFilmFormDTO(filmRepository.findById(id).get());
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film findFilm(Long id) {
        return filmRepository.findById(id).get();
    }

    @Override
    public List<FilmFormDTO> latestFilms() {
        return filmFormMapper.FilmsToFilmFormsDTO(filmRepository.findFirst20ByOrderByReleaseDateDesc());
    }

    @Override
    public List<FilmFormDTO> oldestFilms() {
        return filmFormMapper.FilmsToFilmFormsDTO(filmRepository.findFirst20ByOrderByReleaseDateAsc());
    }

    @Override
    public List<FilmFormDTO> latestGenreFilms(Genre genre) {
        return filmFormMapper.FilmsToFilmFormsDTO(filmRepository.findFirst20ByGenresOrderByReleaseDateDesc(genre));
    }

    @Override
    public void deleteFilmById(Long id) {
        filmRepository.deleteById(id);
    }

    @Override
    public void uploader(MultipartFile filmFile) {
        System.out.println("Processing filmFile (service): " + filmFile);

        try {
            InputStream stream = filmFile.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);  /// this will read 1st workbook of ExcelSheet

            Iterator<Row> rowIterator = sheet.iterator();
            Film film;

            while (rowIterator.hasNext()) {
                // Get the next row in the spreadsheet
                Row row = rowIterator.next();

                // Skip the heading row
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Create a new film object each time
                film = new Film();

                DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                Date releaseDate = null;

                // Get the cells values from the current row
                String title = row.getCell(0).toString();

                try {
                    releaseDate = formatter.parse(row.getCell(1).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String description = row.getCell(2).toString();
                Genre genre1 = genreService.findByName(row.getCell(3).toString());
                Genre genre2 = genreService.findByName(row.getCell(4).toString());
                String rating = row.getCell(5).toString();
                Classification classification = classificationService.findByRating(rating.replaceAll("\\.0*$", ""));
                Boolean status = row.getCell(9).toString().equals("t") ? true : false;

                // Now assign retrieved values to a film entity
                film.setTitle(title);
                film.setReleaseDate(releaseDate);
                film.setDescription(description);
                film.addGenre(genre1);
                film.addGenre(genre2);
                film.setClassification(classification);
                film.setStatus(status);

                System.out.println("Spreadsheet Title: " + film.getTitle() + " Release Date: " + film.getReleaseDate() + " Description: " + film.getDescription());

                // Save the film
                filmRepository.saveAndFlush(film);
            }
        } catch (IOException ex){
            System.out.println(ex);
        }
    }
}