package org.example.application.console.commands.create;

import org.example.application.interfaces.IApplicationEngine;
import org.example.application.console.commands.CommandBase;
import org.example.enums.EAccountType;
import org.example.records.Account;

import java.util.Scanner;

public class CreateAccountCommand extends CommandBase {

    public CreateAccountCommand(IApplicationEngine applicationEngine) {
        super(applicationEngine);
    }

    @Override
    public void execute() {
        var scanner = new Scanner(System.in);

        var clients = applicationEngine.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("There are no clients in the system");
            return;
        }

        var banks = applicationEngine.getAllBanks();
        if (banks.isEmpty()) {
            System.out.println("There are no banks in the system");
            return;
        }

        // choose client
        clients.forEach(System.out::println);
        int clientId;
        try {
            System.out.print("Choose client id for your account: ");
            clientId = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid client id");
            return;
        }

        // choose bank
        banks.forEach(System.out::println);
        int bankId;
        try {
            System.out.print("Choose bank id for your account: ");
            bankId = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid bank id");
            return;
        }

        System.out.println("Choose account type:");
        System.out.println("1. Checking");
        System.out.println("2. Deposit");
        System.out.println("3. Credit");
        EAccountType accountType;
        try {
            int accountTypeInt = scanner.nextInt();
            accountType = EAccountType.values()[accountTypeInt - 1];
        } catch (Exception e) {
            System.out.println("Invalid account type");
            return;
        }
        try {
            applicationEngine.createAccount(new Account(clientId, bankId, 0, accountType));
            System.out.println("Account created");
        } catch (Exception e) {
            System.out.println("Account creation failed");
            System.out.println(e.getMessage());
        }
    }

}
