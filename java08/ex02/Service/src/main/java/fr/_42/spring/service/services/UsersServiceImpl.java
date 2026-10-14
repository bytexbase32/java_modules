
package fr._42.spring.service.services;

import fr._42.spring.service.models.User;
import fr._42.spring.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final SecureRandom random = new SecureRandom();

    @Autowired
    public UsersServiceImpl(
            @Qualifier("usersRepositoryJdbcTemplate")
            UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public String signUp(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        Optional<User> existing = usersRepository.findByEmail(email);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        String password = generatePassword();
        User user = new User(null, email, password);
        usersRepository.save(user);

        return password;
    }

    private String generatePassword() {
        String characters =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            password.append(
                    characters.charAt(random.nextInt(characters.length()))
            );
        }

        return password.toString();
    }
}
