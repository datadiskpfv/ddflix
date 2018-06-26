package uk.co.datadisk.ddflix.entities.user;

import lombok.*;
import uk.co.datadisk.ddflix.entities.AbstractDomainClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "users", callSuper = false)
@ToString(exclude = "users")
@Entity
@Table(name = "profiles")
public class Profile extends AbstractDomainClass {

    @Column(name = "preferred_disc_format")
    private String preferred_disc_format;

}
