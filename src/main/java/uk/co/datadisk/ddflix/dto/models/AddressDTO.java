package uk.co.datadisk.ddflix.dto.models;

import lombok.*;
import uk.co.datadisk.ddflix.entities.user.User;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    private Long id;

    @Size(min = 1, max = 5)
    private String shippingAddressNumber;
    private String shippingAddressName;
    private String shippingAddressStreet1;
    private String shippingAddressStreet2;
    private String shippingAddressCity;
    private String shippingAddressCounty;
    private String shippingAddressCountry;
    private boolean defaultAddress;

    @Size(min = 6, max = 7)
    private String shippingAddressPostcode;
    private User user;
}
