package uk.co.datadisk.ddflix.dto.models;

import lombok.*;
import uk.co.datadisk.ddflix.entities.user.City;
import uk.co.datadisk.ddflix.entities.user.User;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    private Long id;

    @Size(min = 1, max = 5)
    private String line1;
    private String line2;
    private String line3;
    private String line4;
    private City city;

    @Size(min = 6, max = 7)
    private String postcode;
    private User user;
}
