package uk.co.datadisk.ddflix.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.ddflix.dto.models.UserRegisterDTO;
import uk.co.datadisk.ddflix.entities.user.User;

@Mapper(componentModel = "spring")
public interface UserRegisterMapper {

    // INSTANCE is used by the tests
    UserRegisterMapper INSTANCE = Mappers.getMapper(UserRegisterMapper.class);

    // User -> UserRegisterDTO
    UserRegisterDTO UserToUserDTO(User user);

    // UserRegisterDTO -> User
    User UserDTOToUser(UserRegisterDTO userRegisterDTO);
}