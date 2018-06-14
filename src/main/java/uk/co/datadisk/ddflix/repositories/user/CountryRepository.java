package uk.co.datadisk.ddflix.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.user.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByName(String country);
}
