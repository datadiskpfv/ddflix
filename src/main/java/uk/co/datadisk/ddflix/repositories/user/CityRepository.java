package uk.co.datadisk.ddflix.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.ddflix.entities.user.City;

public interface CityRepository extends JpaRepository<City, Long> {

    City findByCity(String city);

//    @Modifying
//    @Transactional
//    @Query(value="LOAD DATA INFILE '\\\\ProgramData\\\\MySQL\\\\MySQL Server 5.7\\\\Uploads\\\\GEODATASOURCE-CITIES-UK-3.TXT' INTO TABLE city\n" +
//            "FIELDS TERMINATED BY '\\t'\n" +
//            "LINES TERMINATED BY '\\r\\n'\n" +
//            "IGNORE 1 LINES;", nativeQuery = true)
//    void bulkLoadData();
}
