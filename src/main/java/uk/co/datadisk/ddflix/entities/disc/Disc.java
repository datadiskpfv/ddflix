package uk.co.datadisk.ddflix.entities.disc;

import lombok.*;
import org.hibernate.annotations.Where;
import uk.co.datadisk.ddflix.entities.AbstractDomainClass;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.FilmsAtHome;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "film", callSuper = false)
@ToString
@Entity
@Table(name = "discs")
public class Disc extends AbstractDomainClass {

    @Column(name="disc_format")
    private String discFormat;

    @Column(name="in_stock")
    private boolean inStock;

    @Column(name="faulty")
    private boolean faulty = false;

    @Column(name="lost")
    private boolean lost = false;

    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId("filmId")
    private Film film;

    // added a where clause as I don't want the returned discs/films
    @OneToMany(mappedBy = "disc", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "returned_date IS NULL")
    @OrderBy("sent_date ASC")
    private List<FilmsAtHome> filmsAtHomes = new ArrayList<>();
}
