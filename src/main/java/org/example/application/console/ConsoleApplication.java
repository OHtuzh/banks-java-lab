package org.example.application.console;

import org.example.application.console.commands.update.UpdateClientDataCommand;
import org.example.application.interfaces.IApplication;
import org.example.application.interfaces.IApplicationEngine;
import org.example.application.console.commands.ICommand;
import org.example.application.console.commands.create.CreateAccountCommand;
import org.example.application.console.commands.create.CreateBankCommand;
import org.example.application.console.commands.create.CreateClientCommand;
import org.example.application.console.commands.list.ListAccountsCommand;
import org.example.application.console.commands.list.ListBanksCommand;
import org.example.application.console.commands.list.ListClientsCommand;
import org.example.application.console.commands.list.ListTransactionsCommand;
import org.example.application.console.commands.transactions.CancelTransactionCommand;
import org.example.application.console.commands.transactions.DepositCommand;
import org.example.application.console.commands.transactions.TransferCommand;
import org.example.application.console.commands.transactions.WithdrawCommand;

import java.util.HashMap;
import java.util.Scanner;

public class ConsoleApplication implements IApplication {
    private final HashMap<Integer, ICommand> commands = new HashMap<>();

    public ConsoleApplication(IApplicationEngine applicationEngine) {
        // List commands
        commands.put(1, new ListClientsCommand(applicationEngine));
        commands.put(2, new ListBanksCommand(applicationEngine));
        commands.put(3, new ListAccountsCommand(applicationEngine));
        commands.put(4, new ListTransactionsCommand(applicationEngine));
        // Create commands
        commands.put(5, new CreateBankCommand(applicationEngine));
        commands.put(6, new CreateClientCommand(applicationEngine));
        commands.put(7, new CreateAccountCommand(applicationEngine));
        // Update commands
        commands.put(8, new UpdateClientDataCommand(applicationEngine));
        // Transaction commands
        commands.put(9, new DepositCommand(applicationEngine));
        commands.put(10, new WithdrawCommand(applicationEngine));
        commands.put(11, new TransferCommand(applicationEngine));
        commands.put(12, new CancelTransactionCommand(applicationEngine));
    }

    public void run() {
        var scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1.  List Clients");
            System.out.println("2.  List Banks");
            System.out.println("3.  List Accounts");
            System.out.println("4.  List Transactions");
            System.out.println("5.  Create Bank");
            System.out.println("6.  Create Client");
            System.out.println("7.  Create Account");
            System.out.println("8.  Update Client Data");
            System.out.println("9.  Deposit");
            System.out.println("10. Withdraw");
            System.out.println("11. Transfer");
            System.out.println("12. Cancel Transaction");
            System.out.println("0.  Exit");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid option!");
                continue;
            }

            if (choice == 0) {
                break;
            }
            var command = commands.get(choice);
            if (command == null) {
                System.out.println("Invalid option!");
                continue;
            }

            command.execute();
        }
    }
}
