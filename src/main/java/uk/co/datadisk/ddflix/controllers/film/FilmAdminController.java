package uk.co.datadisk.ddflix.controllers.film;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uk.co.datadisk.ddflix.dto.models.film.FilmFormDTO;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.Genre;
import uk.co.datadisk.ddflix.services.ImageService;
import uk.co.datadisk.ddflix.services.film.ClassificationService;
import uk.co.datadisk.ddflix.services.film.FilmService;
import uk.co.datadisk.ddflix.services.film.GenreService;
import uk.co.datadisk.ddflix.validators.FilmFormDTOValidator;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))  // will autowire the NonNull attributes
@RequestMapping("/film/film/")
@PreAuthorize("hasAuthority('ADMIN')")
public class FilmAdminController {

    @NonNull
    private final FilmService filmService;

    @NonNull
    private final FilmFormDTOValidator filmFormDTOValidator;

    @NonNull
    private final GenreService genreService;

    @NonNull
    private final ClassificationService classificationService;

    @NonNull
    private final ImageService imageService;

    // READ
    @GetMapping("list")
    public String listFilm(Model model){
        model.addAttribute("films", filmService.findAll());
        return "/film/film/list";
    }

    @GetMapping("{filmId}/info")
    public String infoFilm(Model model, @PathVariable Long filmId){
        FilmFormDTO filmFormDTO = filmService.findFilmDTO(filmId);
        String firstLetter = filmFormDTO.getTitle().substring(0, 1).toUpperCase();

        model.addAttribute("firstLetter", firstLetter);
        model.addAttribute("filmFormDTO", filmFormDTO);
        return "/film/film/info";
    }

    @GetMapping("uploader")
    public String uploaderFilm(){
        return "/film/film/uploader";
    }

    @GetMapping("{filmId}/imagesUpload")
    public String imagesUpload(Model model, @PathVariable Long filmId){
        FilmFormDTO filmFormDTO = filmService.findFilmDTO(filmId);
        String firstLetter = filmFormDTO.getTitle().substring(0, 1).toUpperCase();

        model.addAttribute("firstLetter", firstLetter);
        model.addAttribute("filmFormDTO", filmFormDTO);
        return "/film/film/imageForm";
    }

    @GetMapping("latestFilms")
    public String latestFilms(Model model){
        model.addAttribute("latestFilms", filmService.latestFilms());
        return "/film/film/latestFilms";
    }

    // CREATE, UPDATE
    @RequestMapping(value = "form", method = {RequestMethod.GET, RequestMethod.POST})
    public String createFilm(@RequestParam("action") String action, @RequestParam(value = "filmId", required = false) Long filmId, Model model, @Valid FilmFormDTO filmFormDTO, BindingResult result){

        model.addAttribute("allGenres", genreService.findAll());
        model.addAttribute("allClassifications", classificationService.findAll());

        if(action.equals("save")) {
            // lets perform some validation
            filmFormDTOValidator.validate(filmFormDTO, result);
            if(result.hasErrors()){
                return "/film/film/form";
            }

            // everything should be validated so lets save and return back to the film list
            filmService.createFilm(filmFormDTO);
            return "redirect:/film/film/list";
        } else if(action.equals("edit")){
            // get film in a filmFormDTO
            filmFormDTO = filmService.findFilmDTO(filmId);
        }

        model.addAttribute("filmFormDTO", filmFormDTO);
        return "/film/film/form";
    }

    // DELETE
    @GetMapping("{filmId}/delete")
    public String deleteFilm(@PathVariable Long filmId){
        filmService.deleteFilmById(filmId);
        return "redirect:/film/film/list";
    }

    //UPLOADER
    @PostMapping("uploader")
    public String uploadProfileImagePost(@RequestParam("filmFile") MultipartFile filmFile) {
        filmService.uploader(filmFile);
        return "redirect:/film/film/list";
    }

    @PostMapping("{filmId}/imagesUpload")
    public String imagesUploadPost(@RequestParam("action") String action, @PathVariable Long filmId, @RequestParam("file") MultipartFile file){

        Film film = filmService.findFilm(filmId);

        String filename = imageService.storeFilmImages(file, filmId, action);
        System.out.println("Filename: " + filename);

        if(action.equals("cover")){
            System.out.println("Cover Image: " + file);
            film.setCoverImage(filename);
        } else if(action.equals("background1")){
            System.out.println("Background One Image: " + file);
            //film.setBgImage1(filename);
        } else if(action.equals("background2")){
            System.out.println("Background Two Image: " + file);
            //film.setBgImage2(filename);
        }

        filmService.saveFilm(film);

        return "/film/film/list";
    }
}