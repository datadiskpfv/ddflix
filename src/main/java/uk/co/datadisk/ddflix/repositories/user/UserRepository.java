package uk.co.datadisk.ddflix.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import uk.co.datadisk.ddflix.entities.user.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);

    List<User> findByFilmsAtHomeAvailableGreaterThan(Integer amount);

    @Modifying  // this will effectively drop all non-flushed changes still pending in the EntityManager
    void deleteUsersByIdIn(Collection<Long> userIds);
}