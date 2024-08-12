package org.example.interfaces.storages;

import org.example.records.Account;

import java.util.List;
import java.util.Map;

public interface IAccountStorage {
    /**
     * Returns account by its id
     * @param id accountId
     * @return Account
     */
    public abstract Account getAccountById(int id) throws Exception;

    /**
     * Adds account to the storage
     * @param account some account
     */
    public abstract void addAccount(Account account);

    /**
     * Updates account data
     * @param accountId is account identifier
     * @param account updated account
     */
    public abstract void updateAccount(int accountId, Account account) throws Exception;

    /**
     * Returns all accounts in the storage
     * @return all accounts in the storage
     */
    public abstract List<Map.Entry<Integer, Account>> getAllAccounts();
}
