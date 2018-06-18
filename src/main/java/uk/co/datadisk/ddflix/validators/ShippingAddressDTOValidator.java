package uk.co.datadisk.ddflix.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import uk.co.datadisk.ddflix.dto.models.AddressDTO;
import uk.co.datadisk.ddflix.services.AddressService;

@Component
public class ShippingAddressDTOValidator implements Validator{

    private final AddressService addressService;

    public ShippingAddressDTOValidator(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AddressDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        System.out.println("Validator checking addressDTO........");

        AddressDTO addressDTO = (AddressDTO) target;

        //if(addressDTO.getUsername().matches("[a-zA-Z0-9]+")){
        //    errors.rejectValue("username", "UsernameIncorrect.userEditFormDTO.usernameText", "Username should only contain letter and numbers");
        //}

    }
}
