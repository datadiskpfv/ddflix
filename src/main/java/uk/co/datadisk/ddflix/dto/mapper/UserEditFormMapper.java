package uk.co.datadisk.ddflix.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.ddflix.dto.models.UserEditFormDTO;
import uk.co.datadisk.ddflix.entities.user.User;

@Mapper(componentModel = "spring")
public interface UserEditFormMapper {

    // INSTANCE is used by the tests
    UserEditFormMapper INSTANCE = Mappers.getMapper(UserEditFormMapper.class);

    // User -> UserEditFormDTO (the mappings is for the conversion from String to Date format - MySQL format)
    @Mapping(target="dateCreated", source="dateCreated", dateFormat="yyyy-MM-dd HH:mm:ss")
    UserEditFormDTO UserToUserEditFormDTO(User user);

    // UserEditFormDTO -> User (the mappings is for the conversion from String to Date format - MySQL format)
    @Mapping(source = "dateCreated", target = "dateCreated", dateFormat="yyy-MM-dd HH:mm:ss")
    User UserEditFormDTOToUser(UserEditFormDTO userEditFormDTO);
}