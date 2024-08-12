package org.example.application.console.commands.list;

import org.example.application.console.commands.CommandBase;
import org.example.application.interfaces.IApplicationEngine;

public class ListBanksCommand extends CommandBase {

    public ListBanksCommand(IApplicationEngine applicationEngine) {
        super(applicationEngine);
    }

    @Override
    public void execute() {
        var banks = applicationEngine.getAllBanks();
        if (banks.isEmpty()) {
            System.out.println("There are no banks");
            return;
        }
        banks.forEach(System.out::println);
    }

}
