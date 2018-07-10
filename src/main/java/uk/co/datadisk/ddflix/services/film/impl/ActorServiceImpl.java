package uk.co.datadisk.ddflix.services.film.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.co.datadisk.ddflix.dto.models.film.ActorFormDTO;
import uk.co.datadisk.ddflix.dto.mapper.actor.ActorFormMapper;
import uk.co.datadisk.ddflix.entities.film.Actor;
import uk.co.datadisk.ddflix.entities.film.ActorImage;
import uk.co.datadisk.ddflix.repositories.film.ActorImageRepository;
import uk.co.datadisk.ddflix.repositories.film.ActorRepository;
import uk.co.datadisk.ddflix.services.film.ActorService;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorFormMapper actorFormMapper;
    private final ActorImageRepository actorImageRepository;

    public ActorServiceImpl(ActorRepository actorRepository, ActorFormMapper actorFormMapper, ActorImageRepository actorImageRepository) {
        this.actorRepository = actorRepository;
        this.actorFormMapper = actorFormMapper;
        this.actorImageRepository = actorImageRepository;
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
    public void imageUpload(Long actorId, String action, String filename) {
        Actor actor = actorRepository.findById(actorId).orElse(null);
        ActorImage actorImage;

        if( actor != null){
            if( action.equals("cover")){
                actor.setCoverImage(filename);
            } else if (action.equals("background")){

                actorImage = actorImageRepository.findByImageName(filename);

                // if image does not exists lets create a new one
                if( actorImage == null ){
                    actorImage = new ActorImage(filename);
                }
                actorImage.addActorImage(actor);
            }
        }
    }

    @Override
    public void deleteActorById(Long id) {
        actorRepository.deleteById(id);
    }
}