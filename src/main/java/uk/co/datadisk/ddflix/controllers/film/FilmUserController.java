package uk.co.datadisk.ddflix.controllers.film;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uk.co.datadisk.ddflix.dto.models.film.FilmFormDTO;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.services.UserService;
import uk.co.datadisk.ddflix.services.film.FilmService;
import uk.co.datadisk.ddflix.services.film.GenreService;
import uk.co.datadisk.ddflix.util.PageWrapper;

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

    // Get the value from the application property file, default is 3
    @Value("${film.wishlist.limit}")
    private Integer WISHLIST_LIMIT;

    // READ
    @GetMapping("{filmId}/infoUser")
    public String infoUserFilm(Model model, @PathVariable Long filmId){
        FilmFormDTO filmFormDTO = filmService.findFilmDTO(filmId);
        String firstLetter = filmFormDTO.getTitle().substring(0, 1).toUpperCase();

        model.addAttribute("firstLetter", firstLetter);
        model.addAttribute("filmFormDTO", filmFormDTO);
        return "/film/film/infoUser";
    }

    @GetMapping("filmList")
    public String filmList(@RequestParam(value = "genre", required = false) String genre,
                           @RequestParam(value = "action", required = false) String action ,Model model, Pageable pageable){
        PageWrapper<Film> page;

        if (genre != null){
            System.out.println("Finding films by Genre: " + genre);
            page = new PageWrapper<>(filmService.findAllByGenre(genre, pageable), "/film/film/filmList");
        } else {
            System.out.println("Finding ALL films");
            page = new PageWrapper<>(filmService.findAll(pageable), "/film/film/filmList");
        }

        if (action != null) {
            page = new PageWrapper<>(filmService.findFilmOptions(action, pageable), "/film/film/filmList");
        }

        System.out.println("Pageable: " + pageable.toString());
        model.addAttribute("genreList", genreService.findAll());
        model.addAttribute("page", page);
        return "/film/film/filmList";
    }

    @PostMapping("search")
    public String filmSearch(@ModelAttribute("keyword") String keyword,
                                 Model model, Pageable pageable){

        System.out.println("Search keyword: " + keyword);
        PageWrapper<Film> page = new PageWrapper<>(filmService.FindFilmBySearchString(keyword, pageable), "/film/film/filmList");
        model.addAttribute("page", page);
        return "/film/film/filmList";
    }

    // MERGE THE BELOW METHODS
    @GetMapping("{filmId}/addToWishlist")
    public String addToWishlist(Model model, @PathVariable Long filmId, @RequestParam("userId") Long userId){

        User user = userService.findUser(userId);

        if(user.getWishlists().size() >= WISHLIST_LIMIT){
            System.out.println("Wish List is FULL!!!!! user " + user.getEmail());
            model.addAttribute("wishListFull", true);
        } else {
            userService.addFilmToWishlist(userId, filmId);
        }

        if(user.getFilmsAtHomes().size() > 0) {
            model.addAttribute("filmsAtHome", user.getFilmsAtHomes());
        }

        model.addAttribute("limit", WISHLIST_LIMIT);
        model.addAttribute("wishlists", user.getWishlists());
        return "/film/film/wishlist";
    }

    @GetMapping("{userId}/wishlist")
    public String wishlist(Model model, @PathVariable Long userId){
        User user = userService.findUser(userId);

        // web page changes when there are no films in wishlist or any films at home
        if( user.getWishlists().size() > 0) {
            model.addAttribute("wishlists", user.getWishlists());
        }

        if(user.getFilmsAtHomes().size() > 0) {
            model.addAttribute("filmsAtHome", user.getFilmsAtHomes());
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
        userService.removeFilmFromWishlist(userId, filmId);
        return "redirect:/film/film/" + userId + "/wishlist";
    }
}