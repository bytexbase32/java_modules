package ex03;

public class Program {
    public static void main(String[] args) {

        UsersList users = new UsersArrayList();

        users.add(new User("USER1", 100));
        users.add(new User("USER2", 200));
        users.add(new User("USER3", 250));
        users.add(new User("USER4", 400));

        User user1 = users.getUserByIndex(0);
        User user2 = users.getUserByIndex(1);
        User user3 = users.getUserByIndex(2);
        User user4 = users.getUserByIndex(3);

        Transaction t1 = new Transaction(user3, user2, TransferCategory.DEBIT, 40);
        Transaction t2 = new Transaction(user3, user2, TransferCategory.DEBIT, 120);
        Transaction t3 = new Transaction(user2, user1, TransferCategory.CREDIT, 80);
        Transaction t4 = new Transaction(user1, user4, TransferCategory.CREDIT, 700);

        // add transactions to USERS
        user3.add(t1);
        user3.add(t2);

        user2.add(t3);
        user1.add(t4);

        // test remove from user3
        try {
            user3.remove(t1.getId());
        } catch (TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // try removing again (should fail)
        try {
            user3.remove(t1.getId());
        } catch (TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // print user3 transactions
        Transaction[] arr = user3.toArray();

        for (int i = 0; i < arr.length; i++) {
            System.out.println(
                    "Transaction: from " + arr[i].getSender().getName()
                            + " to " + arr[i].getRecipient().getName()
                            + " type " + arr[i].getCategory()
                            + " amount " + arr[i].getAmount());
        }
    }
}