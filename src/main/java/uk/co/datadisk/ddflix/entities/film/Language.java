package uk.co.datadisk.ddflix.entities.film;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.datadisk.ddflix.entities.AbstractDomainClass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"filmLanguages", "filmSubtitles"}, callSuper = false)
@Entity
@Table(name = "language")
public class Language extends AbstractDomainClass {

    @Column(name = "language")
    private String language;

    @ManyToMany(mappedBy = "languages")
    private Set<Film> filmLanguages = new HashSet<>();

    @ManyToMany(mappedBy = "subtitles")
    private Set<Film> filmSubtitles = new HashSet<>();
}