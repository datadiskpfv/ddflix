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
import uk.co.datadisk.ddflix.dto.models.disc.DiscFormDTO;
import uk.co.datadisk.ddflix.dto.models.film.FilmFormDTO;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.services.ImageService;
import uk.co.datadisk.ddflix.services.disc.DiscService;
import uk.co.datadisk.ddflix.services.film.ClassificationService;
import uk.co.datadisk.ddflix.services.film.FilmService;
import uk.co.datadisk.ddflix.services.film.GenreService;
import uk.co.datadisk.ddflix.validators.FilmFormDTOValidator;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))  // will autowire the NonNull attributes
@RequestMapping("/film/actor/")
@PreAuthorize("hasAuthority('ADMIN')")
public class ActorAdminController {

    @NonNull
    private final ActorService actorService;

    @NonNull
    private final ImageService imageService;

    // READ
    @GetMapping("list")
    public String listFilm(Model model){
        model.addAttribute("actors", actorService.findAll());
        return "/film/actor/list";
    }

    @GetMapping("{actorId}/info")
    public String infoFilm(Model model, @PathVariable Long actorId){
        return "/film/actor/info";
    }

    @GetMapping("{actorId}/imagesUpload")
    public String imagesUpload(Model model, @PathVariable Long filmId){
        return "/film/actor/imageForm";
    }


    // CREATE, UPDATE
    @RequestMapping(value = "form", method = {RequestMethod.GET, RequestMethod.POST})
    public String createActor(@RequestParam("action") String action, @RequestParam(value = "actorId", required = false) Long actorId, Model model, @Valid ActorFormDTO actorFormDTO, BindingResult result){
        return "/film/actor/form";
    }

    // DELETE
    @GetMapping("{actorId}/delete")
    public String deleteActor(@PathVariable Long actorId){
        actorService.deleteActorById(actorId);
        return "redirect:/film/actor/list";
    }
}