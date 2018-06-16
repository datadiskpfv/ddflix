package uk.co.datadisk.ddflix.entities.film;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Embeddable
public class ReviewId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "film_id")
    private Long filmId;
}
