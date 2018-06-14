package uk.co.datadisk.ddflix.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.user.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}