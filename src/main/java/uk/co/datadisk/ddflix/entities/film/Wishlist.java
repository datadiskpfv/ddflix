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
@EqualsAndHashCode(exclude = {"id", "wishedOn"})
@Entity
@Table(name = "wishlists")
public class Wishlist {

    @EmbeddedId
    private WishlistId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    private Film film;

    @Column(name = "wished_on", updatable = false)
    private Date wishedOn = new Date();

    public Wishlist(User user, Film film) {
        this.user = user;
        this.film = film;
        this.id = new WishlistId(user.getId(), film.getId());
    }
}