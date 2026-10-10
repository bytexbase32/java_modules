
package fr._42.services;

import fr._42.exceptions.AlreadyAuthenticatedException;
import fr._42.models.User;
import fr._42.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersServiceImplTest {
    private UsersRepository usersRepository;
    private UsersServiceImpl usersService;

    @BeforeEach
    void setUp() {
        usersRepository = mock(UsersRepository.class);
        usersService = new UsersServiceImpl(usersRepository);
    }

    @Test
    void authenticateReturnsTrueAndUpdatesUserForCorrectCredentials() {
        User user = new User(1, "junior", "secret", false);
        when(usersRepository.findByLogin("junior")).thenReturn(user);

        boolean result = usersService.authenticate("junior", "secret");

        assertTrue(result);
        assertTrue(user.isAuthenticated());
        verify(usersRepository).update(user);
    }

    @Test
    void authenticateThrowsWhenUserIsAlreadyAuthenticated() {
        User user = new User(1, "junior", "secret", true);
        when(usersRepository.findByLogin("junior")).thenReturn(user);

        assertThrows(
                AlreadyAuthenticatedException.class,
                () -> usersService.authenticate("junior", "secret")
        );

        verify(usersRepository, never()).update(any(User.class));
    }

    @Test
    void authenticateReturnsFalseForIncorrectPassword() {
        User user = new User(1, "junior", "secret", false);
        when(usersRepository.findByLogin("junior")).thenReturn(user);

        boolean result = usersService.authenticate("junior", "wrong");

        assertFalse(result);
        assertFalse(user.isAuthenticated());
        verify(usersRepository, never()).update(any(User.class));
    }


    @Test
    void authenticatePropagatesExceptionForIncorrectLogin() {
        when(usersRepository.findByLogin("unknown"))
                .thenThrow(new RuntimeException("User not found"));

        assertThrows(
                RuntimeException.class,
                () -> usersService.authenticate("unknown", "secret")
        );

        verify(usersRepository, never()).update(any(User.class));
    }

}
