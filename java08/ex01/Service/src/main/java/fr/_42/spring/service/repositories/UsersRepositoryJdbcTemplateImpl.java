
package fr._42.spring.service.repositories;

import fr._42.spring.service.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<User> mapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getLong("id"), rs.getString("email"));
        }
    };

    public User findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT id, email FROM users WHERE id = ?",
                    mapper, id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT id, email FROM users", mapper
        );
    }

    public void save(User user) {
        jdbcTemplate.update(
                "INSERT INTO users(email) VALUES (?)",
                user.getEmail()
        );
    }

    public void update(User user) {
        jdbcTemplate.update(
                "UPDATE users SET email = ? WHERE id = ?",
                user.getEmail(), user.getId()
        );
    }

    public void delete(Long id) {
        jdbcTemplate.update(
                "DELETE FROM users WHERE id = ?", id
        );
    }

    public Optional<User> findByEmail(String email) {
        List<User> users = jdbcTemplate.query(
                "SELECT id, email FROM users WHERE email = ?",
                mapper, email
        );
        return users.isEmpty()
                ? Optional.<User>empty()
                : Optional.of(users.get(0));
    }
}
