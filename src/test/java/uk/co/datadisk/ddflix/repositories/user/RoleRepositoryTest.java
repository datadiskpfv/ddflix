package uk.co.datadisk.ddflix.repositories.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.ddflix.DdflixApplication;
import uk.co.datadisk.ddflix.entities.user.Role;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DdflixApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Before
    public void setUp() throws Exception {
        Role role = new Role();
        role.setName("Test");
        roleRepository.save(role);
    }

    @Test
    @Transactional
    public void checkRole(){
        Role role = roleRepository.findByName("Test");
        assertNotNull(role);
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void createRole(){
        Role role = new Role();
        role.setName("Dummy Role");
        roleRepository.save(role);

        Role role1 = roleRepository.findByName("Dummy Role");
        assertNotNull(role1);
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void deleteRole(){
        Role role = roleRepository.findByName("Test");
        assertNotNull(role);
        roleRepository.delete(role);

        assertNull(roleRepository.findByName("Test"));
    }

    @Test
    @Transactional
    //@Rollback(false)
    public void upDateRole(){
        Role role = roleRepository.findByName("Test");
        role.setName("Test - updated");
        roleRepository.save(role);

        assertEquals("Test - updated", roleRepository.findByName("Test - updated").getName());
    }
}