package fr._42.chat.models;

import java.util.List;

public class ChatRoom {
    private Long id;
    private String name;
    private User owner;
    private List<Message> messages;

    public ChatRoom() {
    }

    public ChatRoom(Long id, String name, User owner, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ChatRoom))
            return false;

        ChatRoom other = (ChatRoom) obj;

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
        return "ChatRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", messages=" + messages +
                '}';
    }
}