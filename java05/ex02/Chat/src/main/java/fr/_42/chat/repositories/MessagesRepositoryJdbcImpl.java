package fr._42.chat.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void save(Message message) {

        if (message.getAuthor() == null ||
                message.getAuthor().getId() == null) {
            throw new NotSavedSubEntityException("Author has no ID");
        }

        if (message.getRoom() == null ||
                message.getRoom().getId() == null) {
            throw new NotSavedSubEntityException("Room has no ID");
        }

        String sql = "INSERT INTO messages(author_id, room_id, text, created_at) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setLong(1, message.getAuthor().getId());
            st.setLong(2, message.getRoom().getId());
            st.setString(3, message.getText());
            st.setTimestamp(4, java.sql.Timestamp.valueOf(message.getDateTime()));

            st.executeUpdate();

            try (ResultSet keys = st.getGeneratedKeys()) {
                if (keys.next()) {
                    message.setId(keys.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}