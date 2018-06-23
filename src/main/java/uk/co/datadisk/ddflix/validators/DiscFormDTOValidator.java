package uk.co.datadisk.ddflix.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import uk.co.datadisk.ddflix.dto.models.disc.DiscFormDTO;

@Component
public class DiscFormDTOValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return DiscFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        System.out.println("Validator checking discFormDTO........");

        DiscFormDTO discFormDTO = (DiscFormDTO) target;

        if(!discFormDTO.getDiscFormat().matches("Blu-Ray|DVD")){
            errors.rejectValue("discFormat", "FormatIncorrect.discFormDTO.nameText", "Disc format should only be Blu-Ray or DVD");
        }
    }
}