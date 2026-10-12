package fr._42.classes;

public class Task {
    private int id;
    private String name;
    private int priority;

    public Task() {
        this.id = 0;
        this.name = "Default task";
        this.priority = 0;
    }

    public Task(String name, int id, int priority) {
        this.name = name;
        this.id = id;
        this.priority = priority;
    }

    public int increasePriority(int value) {
        this.priority += value;
        return this.priority;
    }

    @Override
    public String toString() {
        return "Task[id=" + id
                + ", name='" + name + '\''
                + ", priority=" + priority + "]";
    }
}