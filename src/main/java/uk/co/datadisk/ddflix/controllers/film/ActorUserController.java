package uk.co.datadisk.ddflix.controllers.film;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uk.co.datadisk.ddflix.dto.models.film.ActorFormDTO;
import uk.co.datadisk.ddflix.dto.models.film.FilmFormDTO;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.services.UserService;
import uk.co.datadisk.ddflix.services.film.ActorService;
import uk.co.datadisk.ddflix.services.film.FilmService;
import uk.co.datadisk.ddflix.services.film.GenreService;
import uk.co.datadisk.ddflix.util.PageWrapper;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))  // will autowire the NonNull attributes
@RequestMapping("/film/actor/")
@PreAuthorize("hasAuthority('USER')")
public class ActorUserController {

    @NonNull
    private final FilmService filmService;

    @NonNull
    private final ActorService actorService;

    // READ
    @GetMapping("{actorId}/infoUser")
    public String infoUserActor(Model model, @PathVariable Long actorId){
        ActorFormDTO actorFormDTO = actorService.findActorDTO(actorId);

        model.addAttribute("actorFormDTO", actorFormDTO);
        return "/film/actor/infoUser";
    }

}