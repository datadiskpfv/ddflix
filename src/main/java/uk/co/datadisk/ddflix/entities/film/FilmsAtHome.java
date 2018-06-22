package uk.co.datadisk.ddflix.entities.film;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.datadisk.ddflix.entities.Disc.Disc;
import uk.co.datadisk.ddflix.entities.user.User;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id", "returned_date"})
@Entity
@Table(name = "films_at_home")
public class FilmsAtHome {

    @EmbeddedId
    private FilmsAtHomeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("discId")
    private Disc disc;

    @Column(name = "returned_date", updatable = false)
    private Date returnedDate;

    public FilmsAtHome(User user, Disc disc) {
        this.user = user;
        this.disc = disc;
        this.id = new FilmsAtHomeId(user.getId(), disc.getId(), new Date());
    }
}