package org.example.interfaces.banks;

import lombok.NonNull;
import org.example.records.Account;
import org.example.records.Bank;
import org.example.records.Transaction;

import java.util.List;
import java.util.Map;

public interface ICentralBank {
    public abstract void registerBank(@NonNull Bank bank) throws Exception;
    public abstract void makeTransferBetweenBanks(int fromAccountId, int toAccountId, int amount) throws Exception;
    public abstract List<Map.Entry<Integer, Bank>> getAllBanks();
    public abstract List<Map.Entry<Integer, Account>> getAllAccounts();
    public abstract List<Map.Entry<Integer, Transaction>> getAllTransactions();
}
