package fr._42.orm;

import fr._42.orm.models.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (OrmManager manager = new OrmManager(User.class)) {

            // 1. Save a new user.
            User user = new User("Sara", "Amrani", 25);
            manager.save(user);

            System.out.println("\n--- AFTER SAVE ---");
            System.out.println("Generated ID: " + user.getId());

            // 2. Retrieve the saved user.
            User found = manager.findById(user.getId(), User.class);

            if (found == null) {
                System.out.println("User not found!");
                return;
            }

            printUser(found);

            // 3. Update the user, including setting a field to null.
            found.setFirstName("Salma");
            found.setLastName(null);
            found.setAge(26);

            manager.update(found);

            // 4. Retrieve again to verify the update.
            System.out.println("\n--- AFTER UPDATE ---");
            User updated = manager.findById(found.getId(), User.class);

            if (updated != null) {
                printUser(updated);
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printUser(User user) {
        System.out.println("ID: " + user.getId());
        System.out.println("First name: " + user.getFirstName());
        System.out.println("Last name: " + user.getLastName());
        System.out.println("Age: " + user.getAge());
    }
}