package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.Rating;
import uk.co.datadisk.ddflix.entities.user.User;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating findByUserAndFilm(User user, Film film);
}
