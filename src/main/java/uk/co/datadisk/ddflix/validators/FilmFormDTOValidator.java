package uk.co.datadisk.ddflix.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import uk.co.datadisk.ddflix.dto.models.film.FilmFormDTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class FilmFormDTOValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return FilmFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        System.out.println("Validator checking filmFormDTO........");

        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

        FilmFormDTO filmFormDTO = (FilmFormDTO) target;

        if(!filmFormDTO.getTitle().matches("[a-zA-Z0-9#!&$ ]+")){
            errors.rejectValue("title", "titleIncorrect.filmFormDTO.titleText", "Film title should only contain letters and symbols #!&$");
        }


//        try {
//            df.parse(filmFormDTO.getReleaseDate());
//        } catch (ParseException e) {
//            errors.rejectValue("releaseDate", "releaseDateIncorrect.filmFormDTO.releaseDateText", "Release date should be in format dd-MMM-yyyy, example 01-Jan-2018");
//            System.out.println("false");
//        }
    }
}