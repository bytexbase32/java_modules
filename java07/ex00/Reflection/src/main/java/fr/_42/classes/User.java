package fr._42.classes;

public class User {
    private int id;
    private String name;
    private int age;

    public User() {
        this.id = 0;
        this.name = "Default name";
        this.age = 0;
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int grow(int value) {
        this.age += value;
        return this.age;
    }

    @Override
    public String toString() {
        return "User[id=" + id
                + ", name='" + name + '\''
                + ", age=" + age + "]";
    }
}