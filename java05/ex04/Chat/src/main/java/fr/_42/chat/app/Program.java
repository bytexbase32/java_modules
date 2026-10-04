package fr._42.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import fr._42.chat.models.ChatRoom;
import fr._42.chat.models.User;
import fr._42.chat.repositories.UsersRepository;
import fr._42.chat.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;

import java.util.List;

public class Program {

    public static void main(String[] args) {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(
                "jdbc:postgresql://localhost:5432/chat_database");
        config.setUsername("postgres");
        config.setPassword("root");

        DataSource dataSource = new HikariDataSource(config);

        UsersRepository repository = new UsersRepositoryJdbcImpl(dataSource);

        List<User> users = repository.findAll(0, 3);

        for (User user : users) {

            System.out.println(
                    "User: " + user.getId() + " " + user.getLogin());

            System.out.println("  Created rooms:");

            for (ChatRoom room : user.getCreatedRooms()) {
                System.out.println(
                        "    " + room.getId() + " " + room.getName());
            }

            System.out.println("  Participating rooms:");

            for (ChatRoom room : user.getRooms()) {
                System.out.println(
                        "    " + room.getId() + " " + room.getName());
            }

            System.out.println();
        }

        ((HikariDataSource) dataSource).close();
    }
}