package ex04;

public class UsersArrayList implements UsersList {

    private User[] users;
    private int capacity;
    private int size;

    public UsersArrayList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        users = new User[capacity];
    }

    public UsersArrayList() {
        this.capacity = 10;
        this.size = 0;
        users = new User[capacity];
    }

    public void add(User user) {
        if (size >= capacity)
            resize(capacity + capacity / 2);
        users[size++] = user;
    }

    public User getUserById(int id) {
        for (int i = 0; i < size; i++) {
            if (users[i].getId() == id)
                return users[i];
        }
        throw new UserNotFoundException("User with id " + id + " has not been found!");
    }

    public User getUserByIndex(int index) {
        if (index < size && index >= 0)
            return users[index];
        throw new UserNotFoundException("User with index " + index + " has not been found!");
    }

    public int getTotalUsers() {
        return size;
    }

    private void resize(int newCapacity) {
        this.capacity = newCapacity;
        User[] newUsers = new User[newCapacity];

        for (int i = 0; i < size; i++) {
            newUsers[i] = users[i];
        }
        users = newUsers;
    }
}
