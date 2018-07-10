package uk.co.datadisk.ddflix.dto.models.film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.datadisk.ddflix.entities.film.ActorImage;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.Gender;
import uk.co.datadisk.ddflix.entities.user.Country;

import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorFormDTO {

    private Long id;

    @Size(min = 1, max = 30)
    private String firstName;

    @Size(min = 1, max = 30)
    private String lastName;

    private Date birthDate;

    private Gender gender;

    private Country birthCountry;

    private String coverImage;

    // define a default create date
    private String dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    private Set<Film> films = new HashSet<>();

    private Set<ActorImage> actorImages = new HashSet<>();
}