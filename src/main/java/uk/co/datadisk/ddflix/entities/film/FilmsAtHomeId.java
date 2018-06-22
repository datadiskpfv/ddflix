package uk.co.datadisk.ddflix.entities.film;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Embeddable
public class FilmsAtHomeId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "disc_id")
    private Long discId;

    // Once set we should not be able to change it
    @Column(name = "sent_date", updatable = false)
    private Date sentDate;
}