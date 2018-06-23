package uk.co.datadisk.ddflix.dto.mapper.disc;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.ddflix.dto.models.disc.DiscFormDTO;
import uk.co.datadisk.ddflix.entities.disc.Disc;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface DiscFormMapper {

    // INSTANCE is used by the tests
    DiscFormMapper INSTANCE = Mappers.getMapper(DiscFormMapper.class);

    // Disc -> DiscFormDTO (the mappings is for the conversion from String to Date format - MySQL format)
    @Mapping(target="dateCreated", source="dateCreated", dateFormat="yyyy-MM-dd HH:mm:ss")
    DiscFormDTO DiscToDiscFormDTO(Disc disc);

    // DiscFormDTO -> Disc (the mappings is for the conversion from String to Date format - MySQL format)
    @Mapping(source = "dateCreated", target = "dateCreated", dateFormat="yyyy-MM-dd HH:mm:ss")
    Disc DiscFormDTOToDisc(DiscFormDTO discFormDTO);
}