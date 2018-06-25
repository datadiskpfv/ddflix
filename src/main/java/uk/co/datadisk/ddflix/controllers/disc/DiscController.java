package uk.co.datadisk.ddflix.controllers.disc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uk.co.datadisk.ddflix.controllers.CommonController;
import uk.co.datadisk.ddflix.dto.models.disc.DiscFormDTO;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.repositories.disc.DiscRepository;
import uk.co.datadisk.ddflix.services.disc.DiscService;
import uk.co.datadisk.ddflix.services.film.FilmService;
import uk.co.datadisk.ddflix.validators.DiscFormDTOValidator;

import javax.validation.Valid;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/admin/film/disc/")
@PreAuthorize("hasAuthority('ADMIN')")
public class DiscController extends CommonController {

    @Autowired
    DiscService discService;

    @Autowired
    DiscFormDTOValidator discFormDTOValidator;

    @Autowired
    FilmService filmService;

    @GetMapping("list")
    public String discList(Model model) {
        model.addAttribute("discList", discService.findAll());
        return "/film/disc/list";
    }

    @RequestMapping(value = "form", method = {RequestMethod.GET, RequestMethod.POST})
    public String editUser(@RequestParam("action") String action, @ModelAttribute("discId") String discId, @RequestParam(value = "filmId", required = false) Long filmId, Model model, @Valid @ModelAttribute("discFormDTO") DiscFormDTO discFormDTO, BindingResult result) {

        // requesting disc edit form passing discId
        if(!discId.equals("0")) {
            System.out.println("Editing disc ID: " + discId);
            // get filled in DTO using discId
            discFormDTO = discService.discForm(parseLong(discId));
            model.addAttribute("discFormDTO", discFormDTO);
        }

        System.out.println("Disc ID: " + discId);
        System.out.println("ACTION: " + action);

        if(action.equals("save")) {

            discFormDTOValidator.validate(discFormDTO, result);
            if(result.hasErrors()){
                return "/film/disc/discEditForm";
            }

            System.out.println("Disc " + discFormDTO.getId() + " saved.");
            System.out.println("Disc created: " + discFormDTO.getDateCreated());
            discService.discFormSave(discFormDTO);
            return "redirect:/admin/film/disc/list";
        }
        return "/film/disc/discEditForm";
    }

    @PostMapping("delete")
    public String deleteDisc(@ModelAttribute("id") String id) {
        discService.deleteDiscById(parseLong(id));
        System.out.println("Deleted disc with ID: " + id);
        return "redirect:/admin/film/disc/list";
    }
}