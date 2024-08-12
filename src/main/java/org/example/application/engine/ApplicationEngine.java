package org.example.application.engine;

import lombok.NonNull;
import org.example.application.interfaces.IApplicationEngine;
import org.example.interfaces.banks.IBank;
import org.example.interfaces.banks.ICentralBank;
import org.example.interfaces.storages.IClientStorage;
import org.example.records.Account;
import org.example.records.Bank;
import org.example.records.Client;
import org.example.records.Transaction;

import java.util.List;
import java.util.Map;

public class ApplicationEngine implements IApplicationEngine {
    private final IClientStorage clientStorage;
    private final IBank bank;
    private final ICentralBank centralBank;

    public ApplicationEngine(IClientStorage clientStorage, IBank bank, ICentralBank centralBank) {
        this.clientStorage = clientStorage;
        this.bank = bank;
        this.centralBank = centralBank;
    }

    @Override
    public @NonNull List<Map.Entry<Integer, Client>> getAllClients() {
        return clientStorage.getClients();
    }

    @Override
    public @NonNull void createClient(@NonNull Client client) {
        clientStorage.addClient(client);
    }

    @Override
    public @NonNull void updateClientData(int clientId, @NonNull Client client) throws Exception {
        clientStorage.updateClient(clientId, client);
    }

    @Override
    public @NonNull List<Map.Entry<Integer, Bank>> getAllBanks() {
        return centralBank.getAllBanks();
    }

    @Override
    public void createBank(@NonNull Bank bank) throws Exception {
        centralBank.registerBank(bank);
    }

    @Override
    public @NonNull List<Map.Entry<Integer, Account>> getAllAccounts() {
        return centralBank.getAllAccounts();
    }

    @Override
    public void createAccount(@NonNull Account account) {
        bank.createAccount(account);
    }

    @Override
    public List<Map.Entry<Integer, Transaction>> getAllTransactions() {
        return centralBank.getAllTransactions();
    }

    @Override
    public void deposit(int accountId, int amount) throws Exception {
        bank.deposit(accountId, amount);
    }

    @Override
    public void withdraw(int accountId, int amount) throws Exception {
        bank.withdraw(accountId, amount);
    }

    @Override
    public void transfer(int fromAccountId, int toAccountId, int amount) throws Exception {
        bank.transfer(fromAccountId, toAccountId, amount);
    }

    @Override
    public void cancelTransaction(int transactionId) throws Exception {
        bank.cancelTransaction(transactionId);
    }

    @Override
    public void skipDays(int days) {

    }
}
