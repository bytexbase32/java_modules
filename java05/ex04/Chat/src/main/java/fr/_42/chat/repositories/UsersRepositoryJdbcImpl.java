package fr._42.chat.repositories;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import fr._42.chat.models.ChatRoom;
import fr._42.chat.models.User;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> findAll(int page, int size) {

        List<User> users = new ArrayList<User>();

        String sql = "WITH page_users AS (" +
                "    SELECT id, login " +
                "    FROM users " +
                "    ORDER BY id " +
                "    LIMIT ? OFFSET ?" +
                "), " +

                "created AS (" +
                "    SELECT owner_id, " +
                "           array_agg(id ORDER BY id) AS room_ids, " +
                "           array_agg(name ORDER BY id) AS room_names " +
                "    FROM chatrooms " +
                "    GROUP BY owner_id" +
                "), " +

                "participated AS (" +
                "    SELECT cu.user_id, " +
                "           array_agg(cr.id ORDER BY cr.id) AS room_ids, " +
                "           array_agg(cr.name ORDER BY cr.id) AS room_names " +
                "    FROM chatroom_users cu " +
                "    JOIN chatrooms cr ON cr.id = cu.room_id " +
                "    GROUP BY cu.user_id" +
                ") " +

                "SELECT u.id, u.login, " +
                "       c.room_ids AS created_ids, " +
                "       c.room_names AS created_names, " +
                "       p.room_ids AS participated_ids, " +
                "       p.room_names AS participated_names " +
                "FROM page_users u " +
                "LEFT JOIN created c ON c.owner_id = u.id " +
                "LEFT JOIN participated p ON p.user_id = u.id " +
                "ORDER BY u.id";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, size);
            stmt.setInt(2, page * size);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    User user = new User();

                    user.setId(rs.getLong("id"));
                    user.setLogin(rs.getString("login"));

                    List<ChatRoom> createdRooms = buildRooms(rs.getArray("created_ids"),
                            rs.getArray("created_names"));

                    List<ChatRoom> rooms = buildRooms(rs.getArray("participated_ids"),
                            rs.getArray("participated_names"));

                    user.setCreatedRooms(createdRooms);
                    user.setRooms(rooms);

                    users.add(user);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    private List<ChatRoom> buildRooms(Array ids, Array names)
            throws SQLException {

        List<ChatRoom> rooms = new ArrayList<ChatRoom>();

        if (ids == null || names == null) {
            return rooms;
        }

        Long[] roomIds = (Long[]) ids.getArray();
        String[] roomNames = (String[]) names.getArray();

        for (int i = 0; i < roomIds.length; i++) {

            ChatRoom room = new ChatRoom();

            room.setId(roomIds[i]);
            room.setName(roomNames[i]);

            room.setMessages(new ArrayList<>());

            rooms.add(room);
        }

        return rooms;
    }
}