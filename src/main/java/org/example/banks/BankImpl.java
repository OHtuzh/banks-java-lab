package org.example.banks;

import org.example.enums.EAccountType;
import org.example.enums.ETransactionType;
import org.example.interfaces.banks.IBank;
import org.example.interfaces.banks.ICentralBank;
import org.example.interfaces.storages.IAccountStorage;
import org.example.interfaces.storages.IBankStorage;
import org.example.interfaces.storages.IClientStorage;
import org.example.interfaces.storages.ITransactionStorage;
import org.example.records.Account;
import org.example.records.Transaction;

public class BankImpl implements IBank {
    private final ITransactionStorage transactionStorage;
    private final IClientStorage clientStorage;
    private final IAccountStorage accountStorage;
    private final IBankStorage bankStorage;
    private final ICentralBank centralBank;

    public BankImpl(
            ITransactionStorage transactionStorage,
            IClientStorage clientStorage,
            IAccountStorage accountStorage,
            IBankStorage bankStorage,
            ICentralBank centralBank
    ) {
        this.transactionStorage = transactionStorage;
        this.clientStorage = clientStorage;
        this.accountStorage = accountStorage;
        this.bankStorage = bankStorage;
        this.centralBank = centralBank;
    }

    @Override
    public void createAccount(Account account) {
        accountStorage.addAccount(account);
    }

    @Override
    public void deposit(int accountId, int amount) throws Exception {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        var account = accountStorage.getAccountById(accountId);
        accountStorage.updateAccount(accountId, new Account(account.clientId(), account.bankId(), account.balance() + amount, account.accountType()));
        transactionStorage.addTransaction(new Transaction(accountId, accountId, amount, ETransactionType.DEPOSIT, false));
    }

    @Override
    public void withdraw(int accountId, int amount) throws Exception {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        var account = accountStorage.getAccountById(accountId);
        var client = clientStorage.getClientById(account.clientId());
        var bankData = bankStorage.getBankById(account.bankId());

        // checking for strange account withdraw
        if ((client.address().isEmpty() || client.passport().isEmpty()) && amount > bankData.maxUnapprovedClientTransaction()) {
            throw new IllegalArgumentException("Passport or address is empty, so you can't withdraw more than " + bankData.maxUnapprovedClientTransaction());
        }

        // checking for not enough balance to withdraw
        if (amount > account.balance() && (account.accountType() != EAccountType.CREDIT || account.balance() + bankData.creditLimit() < amount)) {
            throw new IllegalArgumentException("Not enough balance to withdraw " + amount + " from " + account);
        }
        accountStorage.updateAccount(accountId, new Account(account.clientId(), account.bankId(), account.balance() - amount, account.accountType()));
        transactionStorage.addTransaction(new Transaction(accountId, accountId, amount, ETransactionType.WITHDRAW, false));
    }

    @Override
    public void transfer(int fromAccountId, int toAccountId, int amount) throws Exception {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        var fromAccount = accountStorage.getAccountById(fromAccountId);
        var fromClient = clientStorage.getClientById(fromAccount.clientId());
        var fromBankData = bankStorage.getBankById(fromAccount.bankId());
        var toAccount = accountStorage.getAccountById(toAccountId);

        // checking for strange account withdraw
        if ((fromClient.address().isEmpty() || fromClient.passport().isEmpty()) && amount > fromBankData.maxUnapprovedClientTransaction()) {
            throw new IllegalArgumentException("Passport or address is empty, so you can't transfer more than " + fromBankData.maxUnapprovedClientTransaction());
        }

        // checking for not enough balance to transfer
        if (amount > fromAccount.balance() && (fromAccount.accountType() != EAccountType.CREDIT || fromAccount.balance() + fromBankData.creditLimit() < amount)) {
            throw new IllegalArgumentException("Not enough balance to transfer " + amount + " from " + fromAccount + " to " + toAccount);
        }

        // checking for between banks transaction
        if (toAccount.bankId() != fromAccount.bankId()) {
            centralBank.makeTransferBetweenBanks(fromAccountId, toAccountId, amount);
        } else {
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
    }

    @Override
    public void cancelTransaction(int transactionId) throws Exception {
        var transaction = transactionStorage.getTransactionById(transactionId);
        if (transaction.canceled()) {
            throw new IllegalArgumentException("Transaction " + transactionId + " has been already canceled");
        }

        var fromAccount = accountStorage.getAccountById(transaction.fromAccountId());
        var toAccount = accountStorage.getAccountById(transaction.toAccountId());

        accountStorage.updateAccount(
                transaction.fromAccountId(),
                new Account(fromAccount.clientId(), fromAccount.bankId(), fromAccount.balance() + transaction.amount(), fromAccount.accountType())
        );
        accountStorage.updateAccount(
                transaction.toAccountId(),
                new Account(toAccount.clientId(), toAccount.bankId(), toAccount.balance() - transaction.amount(), toAccount.accountType())
        );
        transactionStorage.updateTransaction(
                transactionId,
                new Transaction(
                        transaction.fromAccountId(),
                        transaction.toAccountId(),
                        transaction.amount(),
                        transaction.transactionType(),
                        true
                )
        );
    }
}
