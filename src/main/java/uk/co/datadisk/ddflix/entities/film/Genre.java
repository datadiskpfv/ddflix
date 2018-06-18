package uk.co.datadisk.ddflix.entities.film;

import lombok.*;
import uk.co.datadisk.ddflix.entities.AbstractDomainClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "genres")
public class Genre extends AbstractDomainClass {

    @Column(name = "name", unique = true)
    private String name;

    @Override
    public String toString(){
        return name;
    }
}
