package org.example.application.console.commands.transactions;

import org.example.application.interfaces.IApplicationEngine;
import org.example.application.console.commands.CommandBase;

import java.util.Scanner;

public class CancelTransactionCommand extends CommandBase {

    public CancelTransactionCommand(IApplicationEngine applicationEngine) {
        super(applicationEngine);
    }

    @Override
    public void execute() {
        var scanner = new Scanner(System.in);

        var transactions = applicationEngine.getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");
            return;
        }

        transactions.forEach(System.out::println);
        int transactionId;
        try {
            System.out.print("Enter transaction id to cancel: ");
            transactionId = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid transaction id");
            return;
        }

        try {
            applicationEngine.cancelTransaction(transactionId);
            System.out.println("Transaction cancelled");
        } catch (Exception e) {
            System.out.println("Error cancelling transaction");
            System.out.println(e.getMessage());
        }
    }

}
