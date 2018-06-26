package uk.co.datadisk.ddflix.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uk.co.datadisk.ddflix.dto.mapper.ProfileMapper;
import uk.co.datadisk.ddflix.dto.mapper.UserEditFormMapper;
import uk.co.datadisk.ddflix.dto.mapper.UserRegisterMapper;
import uk.co.datadisk.ddflix.dto.models.ProfileDTO;
import uk.co.datadisk.ddflix.dto.models.UserEditFormDTO;
import uk.co.datadisk.ddflix.dto.models.UserRegisterDTO;
import uk.co.datadisk.ddflix.entities.disc.Disc;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.FilmsAtHome;
import uk.co.datadisk.ddflix.entities.film.Wishlist;
import uk.co.datadisk.ddflix.entities.user.*;
import uk.co.datadisk.ddflix.exceptions.NotFoundException;
import uk.co.datadisk.ddflix.repositories.disc.DiscRepository;
import uk.co.datadisk.ddflix.repositories.film.FilmsAtHomeRepository;
import uk.co.datadisk.ddflix.repositories.user.PasswordResetTokenRepository;
import uk.co.datadisk.ddflix.repositories.user.UserRepository;
import uk.co.datadisk.ddflix.services.ImageService;
import uk.co.datadisk.ddflix.services.RoleService;
import uk.co.datadisk.ddflix.services.UserService;
import uk.co.datadisk.ddflix.services.film.FilmService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRegisterMapper userRegisterMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserEditFormMapper userEditFormMapper;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final RoleService roleService;
    private final ImageService imageService;
    private final FilmService filmService;
    private final DiscRepository discRepository;
    private final FilmsAtHomeRepository filmsAtHomeRepository;
    private final ProfileMapper profileMapper;

    public UserServiceImpl(UserRepository userRepository, UserRegisterMapper userRegisterMapper, BCryptPasswordEncoder bCryptPasswordEncoder, UserEditFormMapper userEditFormMapper, PasswordResetTokenRepository passwordResetTokenRepository, RoleService roleService, ImageService imageService, FilmService filmService, DiscRepository discRepository, FilmsAtHomeRepository filmsAtHomeRepository, ProfileMapper profileMapper) {
        this.userRepository = userRepository;
        this.userRegisterMapper = userRegisterMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userEditFormMapper = userEditFormMapper;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.roleService = roleService;
        this.imageService = imageService;
        this.filmService = filmService;
        this.discRepository = discRepository;
        this.filmsAtHomeRepository = filmsAtHomeRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public UserRegisterDTO createUser(UserRegisterDTO userRegisterDTO) {
        User user = userRegisterMapper.UserDTOToUser(userRegisterDTO);
        String username = user.getEmail().split("@")[0].replace(".", "");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUsername(username);
        user.setSubscription(3);
        user.setFilmsAtHomeAvailable(0);

        if(user.getRoles().size() == 0){
            Role userRole = roleService.findRole(2L);
            user.addRole(userRole);
        }
        userRegisterDTO = userRegisterMapper.UserToUserDTO(userRepository.saveAndFlush(user));

        return userRegisterDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User Not Found. For ID value: " + id.toString()));

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        Profile profile = new Profile();
        profile.setPreferred_disc_format("Blu-Ray");
        user.setUserProfile(profile);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean checkForExistingUser(UserRegisterDTO userRegisterDTO){
        User user = userRegisterMapper.UserDTOToUser(userRegisterDTO);
        User existingUser = userRepository.findByEmail(user.getEmail());
        return existingUser == null;
    }

    @Override
    public UserEditFormDTO userForm(Long id) {
        User user = userRepository.getOne(id);
        return userEditFormMapper.UserToUserEditFormDTO(user);
    }

    @Override
    public UserEditFormDTO userFormSave(UserEditFormDTO userEditFormDTO) {
        //Got to get the users password as it is not in the DTO
        String userPassword = this.findUser(userEditFormDTO.getId()).getPassword();
        User user = userEditFormMapper.UserEditFormDTOToUser(userEditFormDTO);
        user.setPassword(userPassword);

        return (userEditFormMapper.UserToUserEditFormDTO(this.saveUser(user)));
    }

    public PasswordResetToken createPasswordResetTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        return passwordResetTokenRepository.save(myToken);
    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        System.out.println(user.getEmail() + " old Password: " + oldPassword);
        System.out.println("MATCH: " + bCryptPasswordEncoder.matches(oldPassword, user.getPassword()));
        return bCryptPasswordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public User updateProfileImage(Long id, MultipartFile profileImageFile) {

        User user = this.findUser(id);
        Byte[] imageFile = imageService.imageToByteConversion(profileImageFile);

        if (user.getUserImages() == null) {
            System.out.println("Creating new UserImage.....");
            UserImages userImages = new UserImages();
            userImages.setId(user.getId());
            userImages.setProfileImage(imageFile);

            System.out.println("Saving user with profile image.....");
            user.setUserImages(userImages);
        } else {
            System.out.println("Found users UserImage.....");
            user.getUserImages().setProfileImage(imageFile);
        }

        return this.saveUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addFilmToWishlist(Long userId, Long filmId) {
        Film film = filmService.findFilm(filmId);
        User user = userRepository.findById(userId).get();

        if(!user.getWishlists().contains(film)){
            user.addFilmToWishList(film);
        }
    }

    @Override
    public void removeFilmFromWishlist(Long userId, Long filmId) {
        Film film = filmService.findFilm(filmId);
        User user = userRepository.findById(userId).get();

        if(user.getWishlists().contains(film)){
            user.removeFilmFromWishlist(film);
        }
    }

    @Override
    public List<User> findUsersThatNeedFilms() {
        return userRepository.findByFilmsAtHomeAvailableGreaterThan(0);
    }

    @Override
    public List<Disc> availableDiscsToSend(Long userId) {
        User user = userRepository.findById(userId).get();
        List<Disc> availableDiscsToSendList = new ArrayList<>();

        for(Wishlist wl : user.getWishlists()){
            String filmTitle = wl.getFilm().getTitle();

            System.out.println("Search for available Blu-Ray disc for film: " + filmTitle);

            for (Disc disc: discRepository.findAvailableDiscsByFilmAndInStockTrueAndFaultyFalseAndLostFalseAndDiscFormat(wl.getFilm(), "Blu-Ray")) {
                if(disc != null) {
                    System.out.println("Adding disc id: " + disc.getId() + " to available Discs film:" + filmTitle);
                    availableDiscsToSendList.add(disc);
                } else {
                    System.out.println("Unable to found Blu-Ray disc for film: " + filmTitle);
                }
            }
        }
        System.out.println("Numbers of available discs: " + availableDiscsToSendList.size());

        return availableDiscsToSendList;
    }

    @Override
    public void sendDiscToUser(Long userId, Long discId) {
        User user = userRepository.findById(userId).orElse(null);
        Disc disc = discRepository.findById(discId).orElse(null);

        if( user != null && disc != null) {
            System.out.println("Reduce user films at home limit by one");
            user.setFilmsAtHomeAvailable(user.getFilmsAtHomeAvailable() - 1);

            System.out.println("Remove film from wishlist");
            user.removeFilmFromWishlist(disc.getFilm());

            System.out.println("add disc to films at home");
            user.addFilmsToHomes(disc);

            System.out.println("change disc in_stock to false");
            disc.setInStock(false);
        }
    }

    @Override
    public void returnDiscFromUser(Long userId, Long discId) {
        User user = userRepository.findById(userId).orElse(null);
        Disc disc = discRepository.findById(discId).orElse(null);
        FilmsAtHome filmsAtHome = filmsAtHomeRepository.findByUserAndDiscAndReturnedDateIsNull(user, disc);

        if( user != null && disc != null) {
            System.out.println("increase user films at home limit by one");
            user.setFilmsAtHomeAvailable(user.getFilmsAtHomeAvailable() + 1);

            System.out.println("remove disc from films at home by setting returned date");
            filmsAtHome.setReturnedDate(new Date());

            System.out.println("change disc in_stock to true");
            disc.setInStock(true);
        }
    }

    @Override
    public void updateProfile(Long userId, ProfileDTO profileDTO) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            user.setUserProfile(profileMapper.ProfileDTOToProfile(profileDTO));
            userRepository.save(user);
        }
    }

    @Override
    public ProfileDTO getUserProfileDTO(long userId) {
        return profileMapper.ProfileToProfileDTO(userRepository.findById(userId).get().getUserProfile());
    }
}