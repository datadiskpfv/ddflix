package uk.co.datadisk.ddflix.entities.film;

import lombok.*;
import uk.co.datadisk.ddflix.entities.AbstractDomainClass;
import uk.co.datadisk.ddflix.entities.Disc.Disc;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"classification","genres", "discs", "actors", "ratings", "reviews", "languages", "subtitles"}, callSuper = false)
@ToString(exclude = {"classification","genres","discs", "actors", "ratings", "reviews", "languages", "subtitles"})
@Entity
@Table(name = "films")
public class Film extends AbstractDomainClass {

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)        //preserves the date only, drops the time element
    private Date releaseDate;

    @Column(name = "status")
    private Boolean status = false;

    @OneToOne
    @JoinColumn(name = "classification_id", nullable = false)
    private Classification classification;

    @Column(name = "cover_image")
    private String coverImage;

    // Lazy loaded by default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="film_genre",
            joinColumns = {@JoinColumn(name="film_id")},
            inverseJoinColumns = {@JoinColumn(name="genre_id")}
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="film_languages",
            joinColumns = {@JoinColumn(name="film_id")},
            inverseJoinColumns = {@JoinColumn(name="language_id")}
    )
    private Set<Language> languages = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="film_subtitles",
            joinColumns = {@JoinColumn(name="film_id")},
            inverseJoinColumns = {@JoinColumn(name="language_id")}
    )
    private Set<Language> subtitles = new HashSet<>();

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disc> discs = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="film_actor",
            joinColumns={@JoinColumn(name="film_id")},
            inverseJoinColumns = {@JoinColumn(name="actor_id")}
    )
    private Set<Actor> actors = new HashSet<>();

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public void addGenre(Genre genre){
        this.genres.add(genre);
    }
    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
    }

    public void addActor(Actor actor){
        this.actors.add(actor);
    }
    public void removeActor(Actor actor) {
        this.actors.remove(actor);
    }

    public void addLanguage(Language language){
        this.languages.add(language);
    }
    public void removeLanguage(Language language){
        this.languages.remove(language);
    }

    public void addSubtitle(Language language){
        this.subtitles.add(language);
    }
    public void removeSubtitle(Language language){
        this.subtitles.remove(language);
    }
}
