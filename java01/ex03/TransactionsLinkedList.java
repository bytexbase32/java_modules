package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private static class Node {
        public Transaction transaction;
        public Node next;

        Node(Transaction transaction) {
            this.transaction = transaction;
            next = null;
        }

        Node(Transaction transaction, Node next) {
            this.transaction = transaction;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;

    TransactionsLinkedList() {
        head = null;
    }

    public void add(Transaction transaction) {
        Node node = new Node(transaction);

        if (head == null) {
            head = tail = node;
            return;
        }

        tail.next = node;
        tail = node;
    }

    public void remove(UUID id) {
        if (head == null)
            throw new TransactionNotFoundException(id);

        if (head.transaction.getId().equals(id)) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.transaction.getId().equals(id)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }

        throw new TransactionNotFoundException(id);
    }

    public Transaction[] toArray() {
        if (head == null)
            return new Transaction[0];
        Node curr = head;
        int length = 0;
        while (curr != null) {
            curr = curr.next;
            length++;
        }

        Transaction[] transactions = new Transaction[length];

        curr = head;
        for (int i = 0; i < length; i++) {
            transactions[i] = curr.transaction;
            curr = curr.next;
        }

        return transactions;
    }
}