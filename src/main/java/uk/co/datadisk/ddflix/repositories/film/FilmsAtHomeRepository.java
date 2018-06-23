package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.disc.Disc;
import uk.co.datadisk.ddflix.entities.film.FilmsAtHome;
import uk.co.datadisk.ddflix.entities.user.User;

import java.util.List;

public interface FilmsAtHomeRepository extends JpaRepository<FilmsAtHome, Long> {

  FilmsAtHome findByUserAndDiscAndReturnedDateIsNull(User user, Disc disc);
  List<FilmsAtHome> findByUserOrderByReturnedDateDesc(User user);
}