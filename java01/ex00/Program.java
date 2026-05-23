package ex00;

public class Program {

    public static void main(String[] args) {
        User alice = new User(1, "Alice", 500);
        User bob = new User(2, "Bob", 300);

        Transaction t1 = new Transaction(bob, alice, TransferCategory.DEBIT, -200);
        Transaction t2 = new Transaction(alice, bob, TransferCategory.CREDIT, 100);

        // manually update balances to demonstrate effect
        alice.setBalance(alice.getBalance() + t2.getAmount() + t1.getAmount());
        bob.setBalance(bob.getBalance() + t2.getAmount() + t1.getAmount());

        // print results
        System.out.println(alice.getName() + " balance: " + alice.getBalance());
        System.out.println(bob.getName() + " balance: " + bob.getBalance());
    }
}