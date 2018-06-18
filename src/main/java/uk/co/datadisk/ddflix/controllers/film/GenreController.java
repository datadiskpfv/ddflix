package uk.co.datadisk.ddflix.controllers.film;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uk.co.datadisk.ddflix.dto.models.film.GenreFormDTO;
import uk.co.datadisk.ddflix.services.film.GenreService;
import uk.co.datadisk.ddflix.validators.GenreFormDTOValidator;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))  // will autowire the NonNull attributes
@RequestMapping("/film/genre/")
@PreAuthorize("hasAuthority('ADMIN')")
public class GenreController {

    @NonNull
    private final GenreService genreService;

    @NonNull
    private final GenreFormDTOValidator genreFormDTOValidator;

    // READ
    @GetMapping("list")
    public String listGenre(Model model){
        model.addAttribute("genres", genreService.findAll());
        return "/film/genre/list";
    }

    // CREATE, UPDATE
    @RequestMapping(value = "form", method = {RequestMethod.GET, RequestMethod.POST})
    public String createGenre(@RequestParam("action") String action, @RequestParam(value = "genreId", required = false) Long genreId, Model model, @Valid GenreFormDTO genreFormDTO, BindingResult result){

        if(action.equals("save")) {
            // lets perform some validation
            genreFormDTOValidator.validate(genreFormDTO, result);

            if(result.hasErrors()){
                return "/film/genre/form";
            } else if (genreService.findByName(genreFormDTO.getName()) != null){
                result.rejectValue("name", "NameAlreadyTaken.genreFormDTO.nameText", "Genre " + genreFormDTO.getName() + " name has already been taken");
                return "/film/genre/form";
            }

            // everything should be validated so lets save and return back to the genre list
            genreService.createGenre(genreFormDTO);
            return "redirect:/film/genre/list";
        } else if(action.equals("edit")){
            // get genre in a genreFormDTO
            genreFormDTO = genreService.findGenre(genreId);
        }

        model.addAttribute("genreFormDTO", genreFormDTO);
        return "/film/genre/form";
    }

    // DELETE
    @GetMapping("{genreId}/delete")
    public String deleteGenre(@PathVariable Long genreId){
        genreService.deleteGenreById(genreId);
        return "redirect:/film/genre/list";
    }
}