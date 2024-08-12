package org.example.application.console.commands.list;

import org.example.application.console.commands.CommandBase;
import org.example.application.interfaces.IApplicationEngine;

public class ListClientsCommand extends CommandBase {

    public ListClientsCommand(IApplicationEngine applicationEngine) {
        super(applicationEngine);
    }

    @Override
    public void execute() {
        var clients = applicationEngine.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("There are no clients");
            return;
        }
        clients.forEach(System.out::println);
    }

}
