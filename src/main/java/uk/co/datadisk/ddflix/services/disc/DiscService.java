package uk.co.datadisk.ddflix.services.disc;

import uk.co.datadisk.ddflix.dto.models.disc.DiscFormDTO;
import uk.co.datadisk.ddflix.entities.disc.Disc;
import uk.co.datadisk.ddflix.entities.film.Film;

import java.util.List;

public interface DiscService {

    Disc saveDisc(Disc disc);

    List<Disc> findAll();

    List<Disc> findAllDiscsOfFilm(Film film, String format);

    void deleteDiscById(Long discId);

    DiscFormDTO discForm(Long id);

    DiscFormDTO discFormSave(DiscFormDTO discFormDTO);
}