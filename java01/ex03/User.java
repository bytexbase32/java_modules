package ex03;

import java.util.UUID;

public class User {

    private final int id;
    private String name;
    private int balance;
    private TransactionsList transactions;

    public User(String name, int balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = Math.max(balance, 0);
        this.transactions = new TransactionsLinkedList();
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public void remove(UUID id) {
        transactions.remove(id);
    }

    public Transaction[] toArray() {
        return transactions.toArray();
    }
}