package org.example.repositories;

import org.example.interfaces.storages.IAccountStorage;
import org.example.records.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepository implements IAccountStorage {
    /**
     * Account storage itself
     */
    protected static HashMap<Integer, Account> accountStorage = new HashMap<>();
    /**
     * Identifier of the next account
     */
    protected static int nextAccountId = 0;

    /**
     * Returns account by its id
     *
     * @param id accountId
     * @return Account
     */
    @Override
    public Account getAccountById(int id) throws Exception {
        var account = accountStorage.get(id);
        if (account == null) {
            throw new Exception("There's no account with such id!");
        }
        return account;
    }

    /**
     * Adds account to the storage
     *
     * @param account some account
     */
    @Override
    public void addAccount(Account account) {
        accountStorage.put(nextAccountId, account);
        nextAccountId++;
    }

    /**
     * Updates account data
     *
     * @param accountId is account identifier
     * @param account   updated account
     */
    @Override
    public void updateAccount(int accountId, Account account) throws Exception {
        if (!accountStorage.containsKey(accountId)) {
            throw new Exception("There's no such account in the storage!");
        }
        accountStorage.put(accountId, account);
    }

    /**
     * Returns all accounts in the storage
     *
     * @return all accounts in the storage
     */
    @Override
    public List<Map.Entry<Integer, Account>> getAllAccounts() {
        return accountStorage.entrySet().stream().toList();
    }
}
