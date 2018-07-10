package uk.co.datadisk.ddflix.entities.film;

import lombok.*;
import uk.co.datadisk.ddflix.entities.AbstractDomainClass;
import uk.co.datadisk.ddflix.entities.user.Country;
import uk.co.datadisk.ddflix.entities.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"birthCountry", "films", "actorImages"}, callSuper = false)
@ToString(exclude = {"birthCountry", "films", "actorImages"})
@Entity
@Table(name = "actors")
public class Actor extends AbstractDomainClass {

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @NotNull
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "cover_image")
    private String coverImage;

    @OneToOne
    @JoinColumn(name = "country")
    private Country birthCountry;

    @ManyToMany(mappedBy = "actors")
    private Set<Film> films = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="actor_image",
            joinColumns={@JoinColumn(name="actor_id")},
            inverseJoinColumns = {@JoinColumn(name="image_id")}
    )
    private Set<ActorImage> actorImages = new HashSet<>();

    public void addActorImage(ActorImage actorImage){
        this.actorImages.add(actorImage);
    }
    public void removeActorImage(ActorImage actorImage) {
        this.actorImages.remove(actorImage);
    }
}
