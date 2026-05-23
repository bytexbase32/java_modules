package ex03;

import java.util.UUID;

enum TransferCategory {
    DEBIT,
    CREDIT
}

public class Transaction {
    private UUID id;
    private User recipient;
    private User sender;
    private TransferCategory category;
    private int amount;

    public Transaction(User recipient, User sender, TransferCategory category, int amount) {
        this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.category = category;

        if (category == TransferCategory.DEBIT && amount < 0) {
            this.amount = amount;
        }
        else if (category == TransferCategory.CREDIT && amount > 0){ 
            this.amount = amount;
        }
        else {
            System.err.println("Invalid transaction amount!");
            this.amount = 0;
        }
    }

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public int getAmount() {
        return amount;
    }

    public TransferCategory getCategory() {
        return category;
    }
}