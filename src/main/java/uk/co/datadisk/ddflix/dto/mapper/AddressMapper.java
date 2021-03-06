package uk.co.datadisk.ddflix.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.ddflix.dto.models.AddressDTO;
import uk.co.datadisk.ddflix.entities.user.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    // INSTANCE is used by the tests
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    // Address -> AddressDTO
    AddressDTO AddressToAddressDTO(Address Address);

    // AddressDTO -> Address
    Address AddressDTOToAddress(AddressDTO AddressDTO);
}