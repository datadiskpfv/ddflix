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
@EqualsAndHashCode(exclude = {"id", "review"})
@Entity
@Table(name = "reviews")
public class Review {

    @EmbeddedId
    private ReviewId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    private Film film;

    @Column(name = "review")
    private String review;

    public Review(User user, Film film) {
        this.user = user;
        this.film = film;
        this.id = new ReviewId(user.getId(), film.getId());
    }

    // used to create rating
    public Review(User user, Film film, String review) {
        this.user = user;
        this.film = film;
        this.id = new ReviewId(user.getId(), film.getId());
        this.review = review;
    }
}