package org.example.application.console.commands.list;

import org.example.application.interfaces.IApplicationEngine;
import org.example.application.console.commands.CommandBase;

public class ListAccountsCommand extends CommandBase {

    public ListAccountsCommand(IApplicationEngine applicationEngine) {
        super(applicationEngine);
    }

    @Override
    public void execute() {
        var accounts = applicationEngine.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found");
            return;
        }
        accounts.forEach(System.out::println);
    }

}
