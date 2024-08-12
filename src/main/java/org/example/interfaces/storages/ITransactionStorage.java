package org.example.interfaces.storages;

import lombok.NonNull;
import org.example.records.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Interface to provide access to transaction storage like db or repository.
 * Have to be stateless.
 */
public interface ITransactionStorage {
    /**
     * Returns all transaction in storage
     * @return all transaction in storage
     */
    public List<Map.Entry<Integer, Transaction>> getAllTransactions();

    /**
     * Returns transaction by its identifier
     * @param id the transaction identifier
     * @return transaction with this id
     * @throws Exception if there's no such transaction
     */
    public abstract @NonNull Transaction getTransactionById(int id) throws Exception;

    /**
     * Adds new transaction to storage
     * @param transaction some transaction
     */
    public abstract void addTransaction(Transaction transaction);

    /**
     * Updates transaction by its id
     * @param transactionId transaction id
     * @param transaction updated transaction
     */
    public abstract void updateTransaction(int transactionId, @NonNull Transaction transaction) throws Exception;

    /**
     * Removes transaction by its identifier
     * @param id transaction identifier
     * @throws Exception if there's no such transaction
     */
    public abstract void removeTransaction(int id) throws Exception;

    /**
     * Drops all data in storage
     */
    public abstract void dropStorage();
}
