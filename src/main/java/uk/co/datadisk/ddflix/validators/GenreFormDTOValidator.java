package uk.co.datadisk.ddflix.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import uk.co.datadisk.ddflix.dto.models.film.GenreFormDTO;

@Component
public class GenreFormDTOValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return GenreFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        System.out.println("Validator checking genreFormDTO........");

        GenreFormDTO genreFormDTO = (GenreFormDTO) target;

        if(!genreFormDTO.getName().matches("[a-zA-Z]+")){
            errors.rejectValue("name", "NameIncorrect.genreFormDTO.nameText", "Genre name should only contain letters");
        }
    }
}