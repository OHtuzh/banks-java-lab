package org.example.application.interfaces;

import lombok.NonNull;
import org.example.records.Account;
import org.example.records.Bank;
import org.example.records.Client;
import org.example.records.Transaction;

import java.util.List;
import java.util.Map;

public interface IApplicationEngine {
    public abstract @NonNull List<Map.Entry<Integer, Client>> getAllClients();
    public abstract @NonNull void createClient(@NonNull Client client);
    public abstract @NonNull void updateClientData(int clientId, @NonNull Client client) throws Exception;

    public abstract @NonNull List<Map.Entry<Integer, Bank>> getAllBanks();
    public abstract void createBank(@NonNull Bank bank) throws Exception;

    public abstract @NonNull List<Map.Entry<Integer, Account>> getAllAccounts();
    public abstract void createAccount(@NonNull Account account);

    public abstract List<Map.Entry<Integer, Transaction>> getAllTransactions();
    public abstract void deposit(int accountId, int amount) throws Exception;
    public abstract void withdraw(int accountId, int amount) throws Exception;
    public abstract void transfer(int fromAccountId, int toAccountId, int amount) throws Exception;
    public abstract void cancelTransaction(int transactionId) throws Exception;

    public abstract void skipDays(int days);
}
