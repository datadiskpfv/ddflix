package uk.co.datadisk.ddflix.dto.models.disc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.FilmsAtHome;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscFormDTO {

    private Long id;
    private String discFormat;
    private boolean inStock;
    private boolean faulty = false;
    private boolean lost = false;

    private Film film;
    private List<FilmsAtHome> filmsAtHomes = new ArrayList<>();

    // define a default create date
    private String dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
}