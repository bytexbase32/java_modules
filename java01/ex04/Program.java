package ex04;

import java.util.UUID;

public class Program {

    public static void main(String[] args) {

        TransactionsService service = new TransactionsService();

        User u1 = new User("USER1", 1000);
        User u2 = new User("USER2", 500);
        User u3 = new User("USER3", 300);

        service.addUser(u1);
        service.addUser(u2);
        service.addUser(u3);

        System.out.println("Initial balances:");
        printBalances(service, u1, u2, u3);

        service.transfer(u1.getId(), u2.getId(), 200);
        service.transfer(u2.getId(), u3.getId(), 100);

        System.out.println("\nAfter transfers:");
        printBalances(service, u1, u2, u3);

        System.out.println("\nUser2 transactions:");
        Transaction[] t = service.getTransfers(u2.getId());

        for (Transaction tr : t) {
            System.out.println(
                    tr.getCategory() + " | " +
                            tr.getAmount() + " | from " +
                            tr.getSender().getName() + " -> " +
                            tr.getRecipient().getName());
        }

        System.out.println("\nUnpaired transactions:");
        Transaction[] bad = service.getUnpairedTransactions();

        for (Transaction tr : bad) {
            System.out.println(tr.getId());
        }

        if (t.length > 0) {
            UUID id = t[0].getId();
            service.removeTransaction(id, u2.getId());
            System.out.println("\nTransaction removed from USER2");
        }
    }

    private static void printBalances(TransactionsService service, User u1, User u2, User u3) {
        System.out.println(u1.getName() + ": " + service.getBalance(u1.getId()));
        System.out.println(u2.getName() + ": " + service.getBalance(u2.getId()));
        System.out.println(u3.getName() + ": " + service.getBalance(u3.getId()));
    }
}