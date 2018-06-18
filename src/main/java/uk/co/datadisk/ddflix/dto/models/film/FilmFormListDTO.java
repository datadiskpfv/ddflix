package uk.co.datadisk.ddflix.dto.models.film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmFormListDTO {
    Set<FilmFormDTO> filmFormListDTO;
}