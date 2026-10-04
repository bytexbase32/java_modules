package fr._42.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr._42.chat.repositories.MessagesRepository;
import fr._42.chat.repositories.MessagesRepositoryJdbcImpl;

import java.time.LocalDateTime;

import javax.sql.DataSource;

import fr._42.chat.models.*;

public class Program {

    public static void main(String[] args) {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:postgresql://localhost:5432/chat_database");
        config.setUsername("postgres");
        config.setPassword("root");

        DataSource dataSource = new HikariDataSource(config);

        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource);


        User author = new User();
        author.setId(1L);

        ChatRoom room = new ChatRoom();
        room.setId(1L);

        Message message = new Message(
                null,
                author,
                room,
                "Hello from Ex02!",
                LocalDateTime.now());

        repository.save(message);

        System.out.println(message.getId());
    }
}