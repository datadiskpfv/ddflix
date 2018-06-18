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
import uk.co.datadisk.ddflix.dto.models.film.ClassificationFormDTO;
import uk.co.datadisk.ddflix.services.ImageService;
import uk.co.datadisk.ddflix.services.film.ClassificationService;
import uk.co.datadisk.ddflix.validators.ClassificationFormDTOValidator;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))  // will autowire the NonNull attributes
@RequestMapping("/film/classification/")
@PreAuthorize("hasAuthority('ADMIN')")
public class ClassificationController {

    @NonNull
    private final ClassificationService classificationService;

    @NonNull
    private final ImageService imageService;

    @NonNull
    private final ClassificationFormDTOValidator classificationFormDTOValidator;

    @GetMapping("list")
    public String listClassification(Model model){
        model.addAttribute("classifications", classificationService.findAll());
        return "/film/classification/list";
    }

    // CREATE, UPDATE
    @RequestMapping(value = "form", method = {RequestMethod.GET, RequestMethod.POST})
    public String createClassification(@RequestParam(value = "image", required = false) MultipartFile classificationImageFile, @RequestParam("action") String action, @RequestParam(value = "classificationId", required = false) Long classificationId, Model model, @Valid ClassificationFormDTO classificationFormDTO, BindingResult result){

        if(action.equals("save")) {
            // lets perform some validation
            classificationFormDTOValidator.validate(classificationFormDTO, result);

            classificationFormDTO.setRating(classificationFormDTO.getRating().toUpperCase());

            // Make sure a file is attached
            if(classificationImageFile.isEmpty()) {
                result.rejectValue("image_name", "ImageNull.classificationFormDTO.imageNullText", "No image was attached");
            }

            if(result.hasErrors()){
                return "/film/classification/form";
            } else if (classificationService.findByRating(classificationFormDTO.getRating()) != null){
                result.rejectValue("rating", "RatingAlreadyTaken.classificationFormDTO.ratingText", "Rating " + classificationFormDTO.getRating() + " name has already been taken");
                return "/film/classification/form";
            }

            String filename = imageService.store(classificationImageFile);

            // everything should be validated so lets save and return back to the classification list
            classificationFormDTO.setImage_name(filename);
            classificationService.createClassification(classificationFormDTO);
            return "redirect:/film/classification/list";
        } else if(action.equals("edit")){
            // get classification in a classificationFormDTO
            classificationFormDTO = classificationService.findClassification(classificationId);
        }

        model.addAttribute("classificationFormDTO", classificationFormDTO);
        return "/film/classification/form";
    }

    // DELETE
    @GetMapping("{classificationId}/delete")
    public String deleteClassification(@PathVariable Long classificationId){
        classificationService.deleteClassificationById(classificationId);
        return "redirect:/film/classification/list";
    }

}