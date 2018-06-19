package uk.co.datadisk.ddflix.services;

import uk.co.datadisk.ddflix.dto.models.AddressDTO;
import uk.co.datadisk.ddflix.entities.user.Address;
import uk.co.datadisk.ddflix.entities.user.User;

public interface AddressService {

    Address findById(Long id);
    void removeById(Long id);
    void saveAddress(AddressDTO addressDTO, Long cityId, Long userId);
    void setDefault(Long userPaymentId, Long userId);
    int getNumberOfAddresses(Long id);
    AddressDTO getAddressDTO(Long id);
}
