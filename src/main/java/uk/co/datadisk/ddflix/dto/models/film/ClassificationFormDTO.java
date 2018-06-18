package uk.co.datadisk.ddflix.dto.models.film;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassificationFormDTO {

    private Long id;
    private String rating;

    @Size(min = 1, max = 10)
    private String image_name;

    // define a default create date
    private String dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
}