package uk.co.datadisk.ddflix.services.film.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.co.datadisk.ddflix.dto.models.film.ActorFormDTO;
import uk.co.datadisk.ddflix.dto.mapper.actor.ActorFormMapper;
import uk.co.datadisk.ddflix.entities.film.Actor;
import uk.co.datadisk.ddflix.repositories.film.ActorRepository;
import uk.co.datadisk.ddflix.services.film.ActorService;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorFormMapper actorFormMapper;

    public ActorServiceImpl(ActorRepository actorRepository, ActorFormMapper actorFormMapper) {
        this.actorRepository = actorRepository;
        this.actorFormMapper = actorFormMapper;
    }

    @Override
    public void createActor(ActorFormDTO actorFormDTO) {
        Actor actor = actorFormMapper.ActorFormDTOToActor(actorFormDTO);

        // I could change the HTML but want to play with date conversion
        //actor.setReleaseDate(DateUtils.dateConvert(actor.getReleaseDate()));
        actorRepository.saveAndFlush(actor);
    }

    @Override
    public void saveActor(Actor actor) {
        actorRepository.saveAndFlush(actor);
    }

    @Override
    public ActorFormDTO findActorDTO(Long id) {
        return actorFormMapper.ActorToActorFormDTO(actorRepository.findById(id).get());
    }

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Page<Actor> findAll(Pageable page) {
        return actorRepository.findAll(page);
    }

//    @Override
//    public Page<Actor> FindActorBySearchString(String searchString, Pageable pageable) {
//        return actorRepository.findDistinctByTitleContainingOrGenresNameContaining(searchString, searchString, pageable);
//    }

    @Override
    public Actor findActor(Long id) {
        return actorRepository.findById(id).get();
    }

    @Override
    public void deleteActorById(Long id) {
        actorRepository.deleteById(id);
    }
}