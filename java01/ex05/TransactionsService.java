package ex05;

import java.util.UUID;

public class TransactionsService {
    private UsersList users;

    TransactionsService() {
        users = new UsersArrayList();
    }

    void addUser(User user) {
        users.add(user);
    }

    public int getBalance(int id) {
        return users.getUserById(id).getBalance();
    }

    public void transfer(int senderId, int recipientId, int amount) {

        User sender = users.getUserById(senderId);
        User recipient = users.getUserById(recipientId);

        if (sender.getBalance() < amount) {
            throw new IllegalTransactionException("Not enough balance");
        }

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        UUID id = UUID.randomUUID();

        Transaction debit = new Transaction(
                id,
                recipient,
                sender,
                TransferCategory.DEBIT,
                -amount);

        Transaction credit = new Transaction(
                id,
                recipient,
                sender,
                TransferCategory.CREDIT,
                amount);

        sender.add(debit);
        recipient.add(credit);
    }

    public Transaction[] getUnpairedTransactions() {

        TransactionsLinkedList result = new TransactionsLinkedList();

        for (int i = 0; i < users.getTotalUsers(); i++) {

            Transaction[] transactions = users.getUserByIndex(i).toArray();

            for (Transaction t : transactions) {

                UUID id = t.getId();
                boolean paired = false;

                for (int j = 0; j < users.getTotalUsers(); j++) {

                    Transaction[] others = users.getUserByIndex(j).toArray();

                    for (Transaction ot : others) {
                        if (ot != t && ot.getId().equals(id)) {
                            paired = true;
                            break;
                        }
                    }

                    if (paired)
                        break;
                }

                if (!paired)
                    result.add(t);
            }
        }

        return result.toArray();
    }

    public Transaction[] getTransfers(int id) {
        return users.getUserById(id).toArray();
    }

    public void removeTransaction(UUID transactionId, int UserId) {
        users.getUserById(UserId).remove(transactionId);
    }

    public User getUser(int id) {
        return users.getUserById(id);
    }
}
