package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.film.ActorImage;

import java.util.List;

public interface ActorImageRepository extends JpaRepository<ActorImage, Long> {
    ActorImage findByImageName(String imageName);
}