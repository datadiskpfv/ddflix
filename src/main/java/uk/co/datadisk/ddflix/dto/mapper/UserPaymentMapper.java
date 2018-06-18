package uk.co.datadisk.ddflix.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.ddflix.dto.models.UserPaymentDTO;
import uk.co.datadisk.ddflix.entities.user.UserPayment;

@Mapper(componentModel = "spring")
public interface UserPaymentMapper {

    // INSTANCE is used by the tests
    UserPaymentMapper INSTANCE = Mappers.getMapper(UserPaymentMapper.class);

    // UserPayment -> UserPaymentDTO
    UserPaymentDTO UserPaymentToUserPaymentDTO(UserPayment userPayment);

    // UserPaymentDTO -> UserPayment
    //@Mapping(ignore = true, target = "id")
    UserPayment UserPaymentDTOToUserPayment(UserPaymentDTO userPaymentDTO);
}