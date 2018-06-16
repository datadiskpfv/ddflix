package uk.co.datadisk.ddflix.entities.film;

import lombok.*;
import uk.co.datadisk.ddflix.entities.AbstractDomainClass;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Disc extends AbstractDomainClass {

    @Column(name="disc_format")
    private String discFormat;

    @Column(name="in_stock")
    private boolean inStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("filmId")
    private Film film;
}
