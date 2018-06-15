package uk.co.datadisk.ddflix.entities.film;

import lombok.*;
import uk.co.datadisk.ddflix.entities.AbstractDomainClass;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"film"})
@ToString(exclude = {"film"})
@Entity
public class Classification extends AbstractDomainClass {

    @Column(name = "rating", unique = true)
    private String rating;

    @Column(name = "image_name", unique = true)
    private String image_name;

    @OneToOne(mappedBy = "classification")
    private Film film;

    @Override
    public String toString(){
        return rating;
    }
}