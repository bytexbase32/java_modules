package fr._42.chat.models;

import java.util.List;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<ChatRoom> createdRooms;
    private List<ChatRoom> rooms;

    public User() {
    }

    public User(Long id, String login, String password,
                List<ChatRoom> createdRooms, List<ChatRoom> rooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ChatRoom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<ChatRoom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<ChatRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<ChatRoom> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof User))
            return false;

        User other = (User) obj;

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
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", rooms=" + rooms +
                '}';
    }
}