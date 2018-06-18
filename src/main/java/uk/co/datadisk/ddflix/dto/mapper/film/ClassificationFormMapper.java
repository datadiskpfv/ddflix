package uk.co.datadisk.ddflix.dto.mapper.film;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.ddflix.dto.models.film.ClassificationFormDTO;
import uk.co.datadisk.ddflix.entities.film.Classification;


@Mapper(componentModel = "spring")
public interface ClassificationFormMapper {

    // INSTANCE is used by the tests
    ClassificationFormMapper INSTANCE = Mappers.getMapper(ClassificationFormMapper.class);

    // Classification -> ClassificationFormDTO (the mappings is for the conversion from String to Date format - MySQL format)
    @Mapping(target="dateCreated", source="dateCreated", dateFormat="yyyy-MM-dd HH:mm:ss")
    ClassificationFormDTO ClassificationToClassificationFormDTO(Classification classification);

    // ClassificationFormDTO -> Classification (the mappings is for the conversion from String to Date format - MySQL format)
    @Mapping(source = "dateCreated", target = "dateCreated", dateFormat="yyy-MM-dd HH:mm:ss")
    Classification ClassificationFormDTOToClassification(ClassificationFormDTO classificationFormDTO);
}