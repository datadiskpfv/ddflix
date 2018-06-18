package uk.co.datadisk.ddflix.entities.film;

import lombok.*;
import uk.co.datadisk.ddflix.entities.AbstractDomainClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"actors"}, callSuper = false)
@ToString(exclude = {"actors"})
@Entity
@Table(name = "actor_images")
public class ActorImage extends AbstractDomainClass {

    @Column(name = "image_name", unique = true)
    private String imageName;

    @ManyToMany(mappedBy = "actorImages")
    private Set<Actor> actors = new HashSet<>();
}
