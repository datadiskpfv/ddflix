package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.film.Actor;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findByFirstNameAndLastName(String first_name, String last_name);
    List<Actor> findByFirstNameContainsOrLastNameContains(String search);
}