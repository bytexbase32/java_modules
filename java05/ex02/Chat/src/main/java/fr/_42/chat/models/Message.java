package fr._42.chat.models;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private User author;
    private ChatRoom room;
    private String text;
    private LocalDateTime dateTime;

    public Message() {
    }

    public Message(Long id, User author, ChatRoom room,
                   String text, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public ChatRoom getRoom() {
        return room;
    }

    public void setRoom(ChatRoom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Message))
            return false;

        Message other = (Message) obj;

        if (id == null || other.id == null)
            return false;

        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", room=" + room +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
