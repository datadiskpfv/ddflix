package uk.co.datadisk.ddflix.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import uk.co.datadisk.ddflix.dto.models.UserEditFormDTO;

@Component
public class UserEditFormDTOValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return UserEditFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        System.out.println("Validator checking userEditFormDTO........");

        UserEditFormDTO userEditFormDTO = (UserEditFormDTO) target;

        if(!userEditFormDTO.getUsername().matches("[a-zA-Z0-9]+")){
            errors.rejectValue("username", "UsernameIncorrect.userEditFormDTO.usernameText", "Username should only contain letter and numbers");
        }
    }
}
