package uk.co.datadisk.ddflix.entities.film;

import lombok.*;
import uk.co.datadisk.ddflix.entities.AbstractDomainClass;
import uk.co.datadisk.ddflix.entities.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"classification","genres"})
@ToString(exclude = {"classification","genres"})
@Entity
public class Film extends AbstractDomainClass {

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)        //preserves the date only, drops the time element
    private Date releaseDate;

    @Column(name = "blu_ray_stock")
    private Integer bluRayStock;

    @Column(name = "dvd_stock")
    private Integer dvdStock;

    @Column(name = "status")
    private Boolean status = false;

    @OneToOne
    @JoinColumn(name = "classification_id", nullable = false)
    private Classification classification;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "bg_image1")
    private String bgImage1;

    @Column(name = "bg_image2")
    private String bgImage2;

    // Lazy loaded by default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="film_genre",
            joinColumns = {@JoinColumn(name="film_id")},
            inverseJoinColumns = {@JoinColumn(name="genre_id")}
    )
    private Set<Genre> genres = new HashSet<>();

    public void addGenre(Genre genre){
        this.genres.add(genre);
    }
    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
    }
}
