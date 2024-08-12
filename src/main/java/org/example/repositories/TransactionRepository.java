package org.example.repositories;

import lombok.NonNull;
import org.example.records.Transaction;
import org.example.interfaces.storages.ITransactionStorage;

import java.util.*;

/**
 * Class that implements ITransactionStorage.
 * Uses static HashMap as a storage.
 */
public class TransactionRepository implements ITransactionStorage {
    /**
     * Transaction storage itself
     */
    protected static HashMap<Integer, Transaction> transactionsStorage = new HashMap<>();
    /**
     * Identifier of the next transaction
     */
    protected static int nextTransactionId = 0;

    /**
     * Returns all transaction in storage
     *
     * @return all transaction in storage
     */
    @Override
    public List<Map.Entry<Integer, Transaction>> getAllTransactions() {
        return new ArrayList<>(transactionsStorage.entrySet());
    }

    /**
     * Returns transaction by its identifier
     *
     * @param id the transaction identifier
     * @return transaction with this id
     * @throws Exception if there's no such transaction
     */
    @Override
    public @NonNull Transaction getTransactionById(int id) throws Exception {
        var transaction = transactionsStorage.get(id);
        if (transaction == null) {
            throw new Exception("Transaction not found");
        }
        return transaction;
    }

    /**
     * Adds new transaction to storage
     *
     * @param transaction some transaction
     */
    @Override
    public void addTransaction(Transaction transaction) {
        transactionsStorage.put(nextTransactionId, transaction);
        nextTransactionId++;
    }

    /**
     * Updates transaction by its id
     *
     * @param transactionId transaction id
     * @param transaction   updated transaction
     */
    @Override
    public void updateTransaction(int transactionId, @NonNull Transaction transaction) throws Exception {
        if (!transactionsStorage.containsKey(transactionId)) {
            throw new Exception("There's no such transaction in the storage");
        }
        transactionsStorage.put(transactionId, transaction);
    }

    /**
     * Removes transaction by its identifier
     *
     * @param id transaction identifier
     * @throws Exception if there's no such transaction
     */
    @Override
    public void removeTransaction(int id) throws Exception {
        if (transactionsStorage.remove(id) == null) {
            throw new Exception("Transaction not found");
        }
    }

    /**
     * Drops all data in storage
     */
    @Override
    public void dropStorage() {
        transactionsStorage.clear();
        nextTransactionId = 0;
    }
}
