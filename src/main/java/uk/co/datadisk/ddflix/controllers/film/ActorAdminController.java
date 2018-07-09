package uk.co.datadisk.ddflix.controllers.film;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uk.co.datadisk.ddflix.dto.models.film.ActorFormDTO;
import uk.co.datadisk.ddflix.repositories.user.CountryRepository;
import uk.co.datadisk.ddflix.services.ImageService;
import uk.co.datadisk.ddflix.services.film.ActorService;
import uk.co.datadisk.ddflix.validators.ActorFormDTOValidator;

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

    @NonNull
    private final CountryRepository countryRepository;

    @NonNull
    private final ActorFormDTOValidator actorFormDTOValidator;

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

        model.addAttribute("countries", countryRepository.findAll());

        if(action.equals("save")){
            System.out.println("Saving Actor");

            // lets perform some validation
            actorFormDTOValidator.validate(actorFormDTO, result);
            if(result.hasErrors()){
                return "/film/actor/form";
            }

            // everything should be validated so lets save and return back to the film list
            actorService.createActor(actorFormDTO);

            return "redirect:/film/actor/list";
        } else if (action.equals("edit")){
            // get actor in a actorFormDTO
            actorFormDTO = actorService.findActorDTO(actorId);
        }

        model.addAttribute("actorFormDTO", actorFormDTO);
        return "/film/actor/form";
    }

    // DELETE
    @GetMapping("{actorId}/delete")
    public String deleteActor(@PathVariable Long actorId){
        actorService.deleteActorById(actorId);
        return "redirect:/film/actor/list";
    }
}