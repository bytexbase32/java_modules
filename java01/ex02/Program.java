package ex02;

public class Program {

    public static void main(String[] args) {
        UsersArrayList users = new UsersArrayList();

        users.add(new User("Yassine_26", 40000));
        users.add(new User("Yassine_25", 22000));
        users.add(new User("Yassine_24", 30000));
        users.add(new User("Yassine_22", 6500));
        users.add(new User("Yassine_19", 10000));

        System.out.println("Total users : " + users.getTotalUsers());

        System.out.println("Users info:\n         -----------------\n");

        for (int i = 0; i < users.getTotalUsers() * 2; i++) {
            try {
                User user = users.getUserByIndex(i);

                System.out.println(
                        "User " + i +
                                "====> [Name : " + user.getName() +
                                ", ID: " + user.getId() +
                                ", Balance: " + user.getBalance() + " ] ");
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("         ....................... ");
        System.out.println("Progrma has finished!");
    }
}