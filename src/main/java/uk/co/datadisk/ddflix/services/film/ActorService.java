package uk.co.datadisk.ddflix.services.film;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uk.co.datadisk.ddflix.dto.models.film.ActorFormDTO;
import uk.co.datadisk.ddflix.entities.film.Actor;

import java.util.List;

public interface ActorService {

    // CRUD methods (create, read, update and delete)
    void createActor(ActorFormDTO actorFormDTO);
    void saveActor(Actor actor);

    ActorFormDTO findActorDTO(Long id);
    List<Actor> findAll();
    Page<Actor> findAll(Pageable page);
    //Page<Actor> FindActorBySearchString(String searchString, Pageable pageable);
    Actor findActor(Long id);

    void deleteActorById(Long id);

}