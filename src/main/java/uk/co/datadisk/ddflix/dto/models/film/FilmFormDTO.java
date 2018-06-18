package uk.co.datadisk.ddflix.dto.models.film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.datadisk.ddflix.entities.film.Classification;
import uk.co.datadisk.ddflix.entities.film.Genre;

import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmFormDTO {

    private Long id;

    @Size(min = 1, max = 30)
    private String title;

    @Size(min = 15, max = 300)
    private String description;

    private Date releaseDate;

    @Size(min = 1, max = 3)
    private String bluRayStock;

    @Size(min = 1, max = 3)
    private String dvdStock;

    private Boolean status = false;

    private Classification classification;

    private String coverImage;

    private String bgImage1;

    private String bgImage2;

    // define a default create date
    private String dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    private Set<Genre> genres = new HashSet<>();
}