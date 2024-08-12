package org.example.application.console.commands.list;

import org.example.application.interfaces.IApplicationEngine;
import org.example.application.console.commands.CommandBase;

public class ListTransactionsCommand extends CommandBase {

    public ListTransactionsCommand(IApplicationEngine applicationEngine) {
        super(applicationEngine);
    }

    @Override
    public void execute() {
        var transactions = applicationEngine.getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("There are no transactions");
            return;
        }
        transactions.forEach(System.out::println);
    }

}
