package uk.co.datadisk.ddflix.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import uk.co.datadisk.ddflix.entities.user.User;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);

    @Modifying  // this will effectively drop all non-flushed changes still pending in the EntityManager
    void deleteUsersByIdIn(Collection<Long> userIds);
}