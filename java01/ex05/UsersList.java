package ex05;

public interface UsersList {
    void add(User user);

    User getUserById(int id);

    User getUserByIndex(int index);

    int getTotalUsers();
}