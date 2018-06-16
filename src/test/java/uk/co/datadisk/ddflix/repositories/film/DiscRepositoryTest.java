package uk.co.datadisk.ddflix.repositories.film;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.datadisk.ddflix.DdflixApplication;
import uk.co.datadisk.ddflix.repositories.user.UserRepository;

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

}