package fr._42.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr._42.chat.repositories.MessagesRepository;
import fr._42.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;

public class Program {

    public static void main(String[] args) {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:postgresql://localhost:5432/chat_database");
        config.setUsername("postgres");
        config.setPassword("root");

        DataSource dataSource = new HikariDataSource(config);

        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource);

        System.out.println(repository.findById(1L));
        System.out.println(repository.findById(999L));
    }
}