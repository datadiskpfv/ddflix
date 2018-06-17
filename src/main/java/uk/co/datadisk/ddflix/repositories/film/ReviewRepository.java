package uk.co.datadisk.ddflix.repositories.film;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.Review;
import uk.co.datadisk.ddflix.entities.user.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review findByUserAndFilm(User user, Film film);
}
