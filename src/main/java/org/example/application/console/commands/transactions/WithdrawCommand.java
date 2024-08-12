package org.example.application.console.commands.transactions;

import org.example.application.interfaces.IApplicationEngine;
import org.example.application.console.commands.CommandBase;

import java.util.Scanner;

public class WithdrawCommand extends CommandBase {

    public WithdrawCommand(IApplicationEngine applicationEngine) {
        super(applicationEngine);
    }

    @Override
    public void execute() {
        var scanner = new Scanner(System.in);

        var accounts = applicationEngine.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("There are no accounts");
            return;
        }

        accounts.forEach(System.out::println);
        int accountId;
        try {
            System.out.print("Enter account id to withdraw: ");
            accountId = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid account id");
            return;
        }

        System.out.print("Enter amount to withdraw: ");
        int amount;
        try {
            amount = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid amount entered.");
            return;
        }

        try {
            applicationEngine.withdraw(accountId, amount);
            System.out.println("Withdrawn " + amount + " to account " + accountId);
        } catch (Exception e) {
            System.out.println("Error withdrawn " + amount + " to account " + accountId);
            System.out.println(e.getMessage());
        }
    }

}
