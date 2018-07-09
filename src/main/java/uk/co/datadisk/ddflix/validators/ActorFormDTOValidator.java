package uk.co.datadisk.ddflix.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import uk.co.datadisk.ddflix.dto.models.film.ActorFormDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class ActorFormDTOValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return ActorFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        System.out.println("Validator checking actorFormDTO........");

        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

        ActorFormDTO actorFormDTO = (ActorFormDTO) target;

        if(!actorFormDTO.getLastName().matches("[a-zA-Z0-9]+")){
            errors.rejectValue("lastName", "lastNameIncorrect.actorFormDTO.lastNameText", "Actor Last Name should only contain letters and/or numbers");
        }
    }
}