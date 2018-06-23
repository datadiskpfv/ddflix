package uk.co.datadisk.ddflix.repositories.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.ddflix.DdflixApplication;
import uk.co.datadisk.ddflix.entities.disc.Disc;
import uk.co.datadisk.ddflix.entities.film.Film;
import uk.co.datadisk.ddflix.entities.film.FilmsAtHome;
import uk.co.datadisk.ddflix.entities.user.User;
import uk.co.datadisk.ddflix.repositories.disc.DiscRepository;
import uk.co.datadisk.ddflix.repositories.user.UserRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class DiscRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DiscRepository discRepository;

    @Autowired
    FilmRepository filmRepository;

    @Test
    @Transactional
    public void userFilmsAtHome(){
        User user1 = userRepository.findByEmail("graham.moffatt@example.com");
        assertEquals(3, user1.getFilmsAtHomes().size());

        User user2 = userRepository.findByEmail("moore.marriott@example.com");
        assertEquals(2, user2.getFilmsAtHomes().size());

    }

    @Test
    @Transactional
    public void availableFilmDiscs(){
        Film film = filmRepository.findById(1L).get();
        List<Disc> availableDiscs = discRepository.findAvailableDiscsByFilmAndInStockTrue(film);
        System.out.println("Available Alien Discs: " + availableDiscs);

        List<Disc> availableDiscsBluRay = discRepository.findAvailableDiscsByFilmAndInStockTrueAndFaultyFalseAndLostFalseAndDiscFormat(film, "Blu-Ray");
        System.out.println("Available Blu-Ray Alien Discs: " + availableDiscsBluRay);

        for(Disc disc : availableDiscsBluRay){
            System.out.println("disc in Stock (Blu-Ray): " + disc.getFilm().getTitle() + " ID: " + disc.getId());
        }

        List<Disc> availableDiscsDvd = discRepository.findAvailableDiscsByFilmAndInStockTrueAndFaultyFalseAndLostFalseAndDiscFormat(film, "DVD");
        System.out.println("Available DVD Alien Discs: " + availableDiscsDvd);

        for(Disc disc : availableDiscsDvd){
            System.out.println("disc in Stock (DVD): " + disc.getFilm().getTitle() + " ID: " + disc.getId());
        }
    }

    @Test
    @Transactional
    public void atHomeFilmDiscs(){
        Film film = filmRepository.findById(1L).get();
        User user = userRepository.findById(2L).get();

        List<Disc> atHomeDiscsBluRay = discRepository.findAvailableDiscsByFilmAndInStockFalseAndDiscFormat(film, "Blu-Ray");
        System.out.println("Available Blu-Ray Alien Discs: " + atHomeDiscsBluRay);
        System.out.println("Blu-Ray Alien Discs at Users Homes: " + atHomeDiscsBluRay.size());

        for(Disc disc : atHomeDiscsBluRay){
            System.out.println("disc at homes (Blu-Ray): " + disc.getFilm().getTitle() + " ID: " + disc.getId());
            for(FilmsAtHome fah : disc.getFilmsAtHomes()){
                System.out.println("User " + fah.getUser().getEmail() + " has " + disc.getFilm().getTitle() + " (Blu-Ray) at home.");
            }
        }

        List<Disc> atHomeDiscsDvd = discRepository.findAvailableDiscsByFilmAndInStockFalseAndDiscFormat(film, "DVD");
        System.out.println("DVD Alien Discs at Users Homes: " + atHomeDiscsDvd.size());
        for(Disc disc : atHomeDiscsDvd){
            System.out.println("disc at homes (DVD): " + disc.getFilm().getTitle() + " ID: " + disc.getId());
            for(FilmsAtHome fah : disc.getFilmsAtHomes()){
                System.out.println("User " + fah.getUser().getEmail() + " has " + disc.getFilm().getTitle() + " (DVD) at home.");
            }
        }
    }
}