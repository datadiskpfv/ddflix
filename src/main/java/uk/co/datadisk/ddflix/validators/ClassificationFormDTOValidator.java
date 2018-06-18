package uk.co.datadisk.ddflix.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import uk.co.datadisk.ddflix.dto.models.film.ClassificationFormDTO;
import uk.co.datadisk.ddflix.dto.models.film.ClassificationFormDTO;

@Component
public class ClassificationFormDTOValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return ClassificationFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        System.out.println("Validator checking classificationFormDTO........");

        ClassificationFormDTO classificationFormDTO = (ClassificationFormDTO) target;

        if(!classificationFormDTO.getRating().matches("[a-zA-Z0-9]+")){
            errors.rejectValue("rating", "RatingIncorrect.classificationFormDTO.ratingText", "Classification Rating should only contain letters and cannot be blank");
        }
    }
}