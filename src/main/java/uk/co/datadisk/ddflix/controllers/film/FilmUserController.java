package uk.co.datadisk.ddflix.controllers.film;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.co.datadisk.ddflix.dto.models.film.FilmFormDTO;
import uk.co.datadisk.ddflix.entities.User;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.services.UserService;
import uk.co.datadisk.ddflix.services.film.FilmService;
import uk.co.datadisk.ddflix.services.film.GenreService;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))  // will autowire the NonNull attributes
@RequestMapping("/film/film/")
@PreAuthorize("hasAuthority('USER')")
public class FilmUserController {

    @NonNull
    private final FilmService filmService;

    @NonNull
    private final GenreService genreService;

    @NonNull
    private final UserService userService;

    // Get the value from the application property file
    @Value("${film.wishlist.limit}")
    private Integer WISHLIST_LIMIT;

    // READ
    @GetMapping("{filmId}/infoUser")
    public String infoUserFilm(Model model, @PathVariable Long filmId){
        FilmFormDTO filmFormDTO = filmService.findFilm(filmId);
        String firstLetter = filmFormDTO.getTitle().substring(0, 1).toUpperCase();

        model.addAttribute("firstLetter", firstLetter);
        model.addAttribute("filmFormDTO", filmFormDTO);
        return "/film/film/infoUser";
    }

    @GetMapping("{filmId}/addToWishlist")
    public String addToWishlist(Model model, @PathVariable Long filmId, @RequestParam("userId") Long userId){

        Film film = filmService.getOne(filmId);
        User user = userService.findUser(userId);

        if(user.getWishlist().size() < WISHLIST_LIMIT){
            user.addToWishlist(film);
            userService.saveUser(user);
        } else {
            System.out.println("Wish List is FULL!!!!! user " + user.getEmail());
            model.addAttribute("wishListFull", "full");
        }

        model.addAttribute("limit", WISHLIST_LIMIT);
        model.addAttribute("wishlist", user.getWishlist());
        return "/film/film/wishlist";
    }

    @GetMapping("{userId}/wishlist")
    public String wishlist(Model model, @PathVariable Long userId){
        User user = userService.findUser(userId);

        // web page changes when there are no films in wishlist or any films at home
        if( user.getWishlist().size() > 0) {
            model.addAttribute("wishlist", user.getWishlist());
        }

        if(user.getFilmsAtHome().size() > 0) {
            model.addAttribute("filmsAtHome", user.getFilmsAtHome());
        }

        return "/film/film/wishlist";
    }

    @GetMapping("carousel")
    public String carousel(Model model){
        model.addAttribute("horrorFilms", filmService.latestGenreFilms(genreService.findByName("Horror")));
        model.addAttribute("actionFilms", filmService.latestGenreFilms(genreService.findByName("Action")));
        model.addAttribute("comedyFilms", filmService.latestGenreFilms(genreService.findByName("Comedy")));
        model.addAttribute("oldestFilms", filmService.oldestFilms());

        model.addAttribute("latestFilms", filmService.latestFilms());
        return "/film/film/carousel";
    }

    // DELETE
    @GetMapping("{filmId}/wishlistDelete")
    public String wishlistDelete(@PathVariable Long filmId, @RequestParam("userId") Long userId){

        Film film = filmService.getOne(filmId);
        User user = userService.findUser(userId);

        user.removeFromWishlist(film);
        userService.saveUser(user);

        return "redirect:/film/film/" + userId + "/wishlist";
    }
}