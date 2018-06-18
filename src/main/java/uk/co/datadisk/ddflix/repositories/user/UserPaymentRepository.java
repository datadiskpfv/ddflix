package uk.co.datadisk.ddflix.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.ddflix.entities.user.UserPayment;

import java.util.List;

public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {

  List<UserPayment> findAllUserPaymentsByUser_Id(Long id);

}