package uk.co.datadisk.ddflix.dto.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.datadisk.ddflix.entities.user.User;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPaymentDTO {

    private Long id;

    private String type;

    @Size(min = 4, max = 20)
    private String cardName;

    @Size(min = 16, max = 16)
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;

    @Size(min = 3, max = 3)
    private int cvc;

    @Size(min = 4, max = 20)
    private String holderName;
    private boolean defaultPayment;

    private User user;
}
