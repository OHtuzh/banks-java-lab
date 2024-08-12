package org.example.banks;

import lombok.NonNull;
import org.example.enums.ETransactionType;
import org.example.interfaces.banks.ICentralBank;
import org.example.interfaces.storages.IAccountStorage;
import org.example.interfaces.storages.IBankStorage;
import org.example.interfaces.storages.ITransactionStorage;
import org.example.records.Account;
import org.example.records.Bank;
import org.example.records.Transaction;

import java.util.List;
import java.util.Map;

public class CentralBank implements ICentralBank {
    private final ITransactionStorage transactionStorage;
    private final IAccountStorage accountStorage;
    private final IBankStorage bankStorage;

    public CentralBank(ITransactionStorage transactionStorage, @NonNull IAccountStorage accountStorage, @NonNull IBankStorage bankStorage) {
        this.transactionStorage = transactionStorage;
        this.accountStorage = accountStorage;
        this.bankStorage = bankStorage;
    }

    @Override
    public void registerBank(@NonNull Bank bank) throws Exception {
        if (bankStorage.getAllBanks().stream().anyMatch(entry -> entry.getValue().name() == bank.name())) {
            throw new Exception("Bank with same name is already exists!");
        }
        bankStorage.addBank(bank);
    }

    @Override
    public void makeTransferBetweenBanks(int fromAccountId, int toAccountId, int amount) throws Exception {
        var fromAccount = accountStorage.getAccountById(fromAccountId);
        var toAccount = accountStorage.getAccountById(toAccountId);

        accountStorage.updateAccount(
                fromAccountId,
                new Account(fromAccount.clientId(), fromAccount.bankId(), fromAccount.balance() - amount, fromAccount.accountType())
        );
        accountStorage.updateAccount(
                toAccountId,
                new Account(toAccount.clientId(), toAccount.bankId(), toAccount.balance() + amount, toAccount.accountType())
        );
        transactionStorage.addTransaction(new Transaction(fromAccountId, toAccountId, amount, ETransactionType.TRANSFER, false));
    }

    @Override
    public List<Map.Entry<Integer, Bank>> getAllBanks() {
        return bankStorage.getAllBanks();
    }

    @Override
    public List<Map.Entry<Integer, Account>> getAllAccounts() {
        return accountStorage.getAllAccounts();
    }

    @Override
    public List<Map.Entry<Integer, Transaction>> getAllTransactions() {
        return transactionStorage.getAllTransactions();
    }
}
