package uk.co.datadisk.ddflix.services;

import org.springframework.web.multipart.MultipartFile;
import uk.co.datadisk.ddflix.dto.models.UserRegisterDTO;
import uk.co.datadisk.ddflix.dto.models.UserEditFormDTO;
import uk.co.datadisk.ddflix.entities.Disc.Disc;
import uk.co.datadisk.ddflix.entities.user.PasswordResetToken;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.entities.film.Film;

import java.util.List;

public interface UserService {

    // CRUD methods (create, read, update and delete)
    UserRegisterDTO createUser(UserRegisterDTO user);

    User findUser(Long id);
    User findUserByEmail(String email);
    List<User> findAll();

    User saveUser(User user);

    void deleteUserById(Long id);

    boolean checkForExistingUser(UserRegisterDTO userRegisterDTO);

    UserEditFormDTO userForm(Long id);

    UserEditFormDTO userFormSave(UserEditFormDTO userEditFormDTO);

    PasswordResetToken createPasswordResetTokenForUser(User user);

    boolean checkIfValidOldPassword(final User user, final String oldPassword);

    void changeUserPassword(final User user, final String password);

    User updateProfileImage(Long id, MultipartFile profileImage);

    User findByUsername(String username);

    void addFilmToWishlist(Long userId, Long filmId);
    void removeFilmFromWishlist(Long userId, Long filmId);

    List<User> findUsersThatNeedFilms();

    List<Disc> availableDiscsToSend(Long userId);
    void sendDiscToUser(Long userId, Long discId);

    void returnDiscFromUser(Long userId, Long discId);
}
