package fr._42.chat.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import javax.sql.DataSource;

// import MessagesRepositoy;
import fr._42.chat.models.Message;
import fr._42.chat.models.User;
import fr._42.chat.models.ChatRoom;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Message> findById(Long id) {

        String sql = "SELECT id, author_id, room_id, text, created_at " +
                "FROM messages WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (!rs.next()) {
                    return Optional.empty();
                }

                Long messageId = rs.getLong("id");
                Long authorId = rs.getLong("author_id");
                Long roomId = rs.getLong("room_id");
                String text = rs.getString("text");
                LocalDateTime dateTime = rs.getTimestamp("created_at").toLocalDateTime();

                User author = new User();
                author.setId(authorId);

                ChatRoom room = new ChatRoom();
                room.setId(roomId);

                Message message = new Message(
                        messageId,
                        author,
                        room,
                        text,
                        dateTime);

                return Optional.of(message);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}