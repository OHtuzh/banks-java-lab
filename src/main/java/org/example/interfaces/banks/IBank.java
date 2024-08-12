package org.example.interfaces.banks;

import org.example.records.Account;
import org.example.records.Bank;

public interface IBank {
    public abstract void createAccount(Account account);

    public abstract void deposit(int accountId, int amount) throws Exception;
    public abstract void withdraw(int accountId, int amount) throws Exception;
    public abstract void transfer(int fromAccountId, int toAccountId, int amount) throws Exception;
    public abstract void cancelTransaction(int transactionId) throws Exception;
}
