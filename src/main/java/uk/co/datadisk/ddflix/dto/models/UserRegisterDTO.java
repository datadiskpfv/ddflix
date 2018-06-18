package uk.co.datadisk.ddflix.dto.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.datadisk.ddflix.annotations.ValidPassword;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {

    @Email
    @Size(min = 10, max = 50)
    private String email;

    @ValidPassword
    private String password;

    @Size(min = 8, max = 15)
    private String passwordConfirm;
}
