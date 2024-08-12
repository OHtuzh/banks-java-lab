package org.example;

import org.example.application.console.ConsoleApplication;
import org.example.application.engine.ApplicationEngine;
import org.example.application.interfaces.IApplication;
import org.example.banks.BankImpl;
import org.example.banks.CentralBank;
import org.example.repositories.AccountRepository;
import org.example.repositories.BankRepository;
import org.example.repositories.ClientRepository;
import org.example.repositories.TransactionRepository;

public class Main {
    public static void main(String[] args) {
        var clientStorage = new ClientRepository();
        var accountStorage = new AccountRepository();
        var transactionStorage = new TransactionRepository();
        var bankStorage = new BankRepository();

        var centralBank = new CentralBank(
                transactionStorage,
                accountStorage,
                bankStorage
        );
        IApplication application = new ConsoleApplication(
                new ApplicationEngine(
                        clientStorage,
                        new BankImpl(
                                transactionStorage,
                                clientStorage,
                                accountStorage,
                                bankStorage,
                                centralBank
                        ),
                        centralBank
                )
        );
        application.run();
    }
}
