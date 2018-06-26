package uk.co.datadisk.ddflix.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.ddflix.dto.models.ProfileDTO;
import uk.co.datadisk.ddflix.entities.user.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    // INSTANCE is used by the tests
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    // Profile -> ProfileDTO
    ProfileDTO ProfileToProfileDTO(Profile Profile);

    // ProfileDTO -> Profile
    Profile ProfileDTOToProfile(ProfileDTO ProfileDTO);
}