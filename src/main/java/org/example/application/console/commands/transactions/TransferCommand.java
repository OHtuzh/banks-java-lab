package org.example.application.console.commands.transactions;

import org.example.application.interfaces.IApplicationEngine;
import org.example.application.console.commands.CommandBase;

import java.util.Scanner;

public class TransferCommand extends CommandBase {

    public TransferCommand(IApplicationEngine applicationEngine) {
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
        int fromAccountId;
        try {
            System.out.print("Enter FROM account id: ");
            fromAccountId = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid account id");
            return;
        }
        if (accounts.stream().noneMatch(entry -> entry.getKey() == fromAccountId)) {
            System.out.println("Account does not exist");
            return;
        }

        int toAccountId;
        try {
            System.out.print("Enter TO account id: ");
            toAccountId = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid account id");
            return;
        }

        System.out.print("Enter amount of transfer: ");
        int amount;
        try {
            amount = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid amount entered.");
            return;
        }

        try {
            applicationEngine.transfer(fromAccountId, toAccountId, amount);
            System.out.println("Transferred " + amount + " from account " + fromAccountId + " to account " + toAccountId);
        } catch (Exception e) {
            System.out.println("Error transferred " + amount + " from account " + fromAccountId + " to account " + toAccountId);
            System.out.println(e.getMessage());
        }
    }

}
