
package fr._42.spring.service.repositories;

import fr._42.spring.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component("usersRepositoryJdbcTemplate")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(
            @Qualifier("hikariDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<User> mapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("id"),
                    rs.getString("email"),
                    rs.getString("password")
            );
        }
    };

    public User findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT id, email, password FROM users WHERE id = ?",
                    mapper, id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT id, email, password FROM users", mapper
        );
    }

    public void save(User user) {
        jdbcTemplate.update(
                "INSERT INTO users(email, password) VALUES (?, ?)",
                user.getEmail(), user.getPassword()
        );
    }

    public void update(User user) {
        jdbcTemplate.update(
                "UPDATE users SET email = ?, password = ? WHERE id = ?",
                user.getEmail(), user.getPassword(), user.getId()
        );
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    public Optional<User> findByEmail(String email) {
        List<User> users = jdbcTemplate.query(
                "SELECT id, email, password FROM users WHERE email = ?",
                mapper, email
        );
        return users.isEmpty()
                ? Optional.<User>empty()
                : Optional.of(users.get(0));
    }
}
