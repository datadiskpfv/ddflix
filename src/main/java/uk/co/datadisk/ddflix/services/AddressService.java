package uk.co.datadisk.ddflix.services;

import uk.co.datadisk.ddflix.dto.models.AddressDTO;
import uk.co.datadisk.ddflix.entities.user.Address;

public interface AddressService {

    Address findById(Long id);
    void removeById(Long id);
    void saveAddress(AddressDTO addressDTO);
    void setDefault(Long userPaymentId, Long userId);
    int getNumberOfAddresses(Long id);
    AddressDTO getAddressDTO(Long id);
}