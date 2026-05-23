package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {

    TransactionsService service;
    boolean devMode;
    Scanner scanner;

    public Menu(boolean mode) {
        service = new TransactionsService();
        devMode = mode;
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {
                    case 1:
                        addUser();
                        break;
                    case 2:
                        viewUserBalances();
                        break;
                    case 3:
                        performTransfer();
                        break;
                    case 4:
                        viewUserTransactions();
                        break;
                    case 5:
                        removeTransfer();
                        break;
                    case 6:
                        checkResults();
                        break;
                    case 7:

                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if ((devMode && choice == 7) || (!devMode && choice == 5))
                break;
            System.out.println("---------------------------------------------------------");
        }
    }

    private void printMenu() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");

        if (devMode) {
            System.out.println("5. DEV - remove a transfer by ID");
            System.out.println("6. DEV - check transfer validity");
            System.out.println("7. Finish execution");
        } else {
            System.out.println("5. Finish execution");
        }
    }

    private void addUser() {
        System.out.println("Enter a user name and a balance");
        String name = scanner.next();
        int age = scanner.nextInt();
        scanner.nextLine();
        User newUser = new User(name, age);
        service.addUser(newUser);
        System.out.println("User with id = " + newUser.getId() + " is added");
    }

    private void viewUserBalances() {
        System.out.println("Enter a user ID");
        int userID = scanner.nextInt();
        scanner.nextLine();

        User user = service.getUser(userID);
        System.out.println(user.getName() + " - " + user.getBalance());
    }

    private void performTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        int senderID = scanner.nextInt();
        int recipientID = scanner.nextInt();
        int transfertAmount = scanner.nextInt();
        scanner.nextLine();
        service.transfer(senderID, recipientID, transfertAmount);
        System.out.println("The transfer is completed");
    }

    private void viewUserTransactions() {
        System.out.println("Enter a user ID");
        int userID = scanner.nextInt();
        scanner.nextLine();
        Transaction[] transactions = service.getTransfers(userID);
        for (Transaction transaction : transactions) {

            if (transaction.getCategory() == TransferCategory.DEBIT) {

                System.out.println(
                        "To "
                                + transaction.getRecipient().getName()
                                + "(id = "
                                + transaction.getRecipient().getId()
                                + ") "
                                + transaction.getAmount()
                                + " with id = "
                                + transaction.getId());

            } else {

                System.out.println(
                        "From "
                                + transaction.getSender().getName()
                                + "(id = "
                                + transaction.getSender().getId()
                                + ") "
                                + transaction.getAmount()
                                + " with id = "
                                + transaction.getId());
            }
        }
    }

    private Transaction findTransaction(int userId, UUID id) {

        Transaction[] transactions = service.getTransfers(userId);

        for (Transaction t : transactions) {
            if (t.getId().equals(id))
                return t;
        }

        return null;
    }

    private void removeTransfer() {
        if (!devMode)
            return;
        System.out.println("Enter a user ID and a transfer ID");
        int userID = scanner.nextInt();
        UUID transferID = UUID.fromString(scanner.next());
        Transaction t = findTransaction(userID, transferID);
        service.removeTransaction(transferID, userID);
        scanner.nextLine();
        System.out.println(
                "Transfer To "
                        + t.getRecipient().getName()
                        + "(id = "
                        + t.getRecipient().getId()
                        + ") "
                        + Math.abs(t.getAmount())
                        + " removed");
    }

    private void checkResults() {
        System.out.println("Check results:");
        Transaction[] transactions = service.getUnpairedTransactions();

        for (Transaction transaction : transactions) {
            System.out.println(transaction.getRecipient().getName() + "(id = " + transaction.getRecipient().getId()
                    + ") has an unacknowledged transfer id = " + transaction.getId() + " from "
                    + transaction.getSender().getName() + "(id = " + transaction.getSender().getId() + ") for "
                    + Math.abs(transaction.getAmount()));
        }
    }
}