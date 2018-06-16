package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.film.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findByFirstNameAndLastName(String first_name, String last_name);
}