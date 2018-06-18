package uk.co.datadisk.ddflix.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.user.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByPostcode(String postcode);
    //List<Address> findAllAddressesByUser_Id(Long id);
}