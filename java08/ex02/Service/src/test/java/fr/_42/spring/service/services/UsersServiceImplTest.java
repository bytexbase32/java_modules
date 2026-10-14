
package fr._42.spring.service.services;

import fr._42.spring.service.config.TestApplicationConfig;
import fr._42.spring.service.models.User;
import fr._42.spring.service.repositories.UsersRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UsersServiceImplTest {
    private AnnotationConfigApplicationContext context;
    private UsersService usersService;
    private UsersRepository usersRepository;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(
                TestApplicationConfig.class
        );

        usersService = context.getBean(UsersService.class);
        usersRepository = context.getBean(
                "usersRepositoryJdbcTemplate",
                UsersRepository.class
        );
    }

    @After
    public void tearDown() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    public void signUpShouldReturnAndSaveTemporaryPassword() {
        String email = "junior@example.com";

        String password = usersService.signUp(email);

        assertNotNull(password);
        assertFalse(password.trim().isEmpty());

        Optional<User> result = usersRepository.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
        assertEquals(password, result.get().getPassword());
    }
}
