package uk.co.datadisk.ddflix.dto.mapper.actor;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.ddflix.dto.models.film.ActorFormDTO;
import uk.co.datadisk.ddflix.entities.film.Actor;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ActorFormMapper {

    // INSTANCE is used by the tests
    ActorFormMapper INSTANCE = Mappers.getMapper(ActorFormMapper.class);

    // Actor -> ActorFormDTO (the mappings is for the conversion from String to Date format - MySQL format)
    @Mapping(target="dateCreated", source="dateCreated", dateFormat="yyyy-MM-dd HH:mm:ss")
    @Mapping(target="birthDate", source="birthDate", dateFormat="yyyy-MM-dd HH:mm:ss")
    ActorFormDTO ActorToActorFormDTO(Actor actor);

    // ActorFormDTO -> Actor (the mappings is for the conversion from String to Date format - MySQL format)
    @Mapping(source = "dateCreated", target = "dateCreated", dateFormat="yyyy-MM-dd HH:mm:ss")
    @Mapping(target="birthDate", source="birthDate", dateFormat="yyyy-MM-dd HH:mm:ss")
    Actor ActorFormDTOToActor(ActorFormDTO actorFormDTO);

    // No ordering on SETS
    Set<ActorFormDTO> ActorsToActorFormsDTO(Set<Actor> actors);

    // Ordering will be determined by the SQL query (repository)
    List<ActorFormDTO> ActorsToActorFormsDTO(List<Actor> actors);
}