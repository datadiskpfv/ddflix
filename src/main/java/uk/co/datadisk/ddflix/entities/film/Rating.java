package uk.co.datadisk.ddflix.entities.film;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.datadisk.ddflix.entities.user.User;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id", "rating"})
@Entity
@Table(name = "ratings")
public class Rating {

    @EmbeddedId
    private RatingId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    private Film film;

    @Column(name = "rating")
    private Integer rating;

    // Used to search for rating
    public Rating(User user, Film film) {
        this.user = user;
        this.film = film;
        this.id = new RatingId(user.getId(), film.getId());
    }

    // used to create rating
    public Rating(User user, Film film, Integer rating) {
        this.user = user;
        this.film = film;
        this.id = new RatingId(user.getId(), film.getId());
        this.rating = rating;
    }
}