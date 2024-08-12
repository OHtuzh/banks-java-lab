package org.example.application.console.commands.update;

import lombok.NonNull;
import org.example.application.console.commands.CommandBase;
import org.example.application.interfaces.IApplicationEngine;
import org.example.records.Client;

import java.util.Scanner;

public class UpdateClientDataCommand extends CommandBase {

    public UpdateClientDataCommand(@NonNull IApplicationEngine applicationEngine) {
        super(applicationEngine);
    }

    @Override
    public void execute() {
        var scanner = new Scanner(System.in);

        var clients = applicationEngine.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("No clients found");
            return;
        }

        clients.forEach(System.out::println);
        int clientId;
        try {
            System.out.println("Enter client ID: ");
            clientId = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid client ID");
            return;
        }

        var clientOptional = clients.stream().filter(entry -> entry.getKey() == clientId).findFirst();
        if (clientOptional.isEmpty()) {
            System.out.println("No client found");
            return;
        }
        var client = clientOptional.get().getValue();

        String address = client.address();
        if (address.isEmpty()) {
            System.out.println("Enter address: ");
            address = scanner.nextLine();
            if (address.isEmpty()) {
                System.out.println("Address is empty. It is ok, but you will have some restrictions.");
            }
        }

        String passport = client.passport();
        if (passport.isEmpty()) {
            System.out.println("Enter passport: ");
            passport = scanner.nextLine();
            if (passport.isEmpty()) {
                System.out.println("Passport is empty. It is ok, but you will have some restrictions.");
            }
        }

        if (address.equals(client.address()) && passport.equals(client.passport())) {
            System.out.println("No changes!");
            return;
        }

        try {
            applicationEngine.updateClientData(clientId, new Client(client.firstName(), client.lastName(), address, passport));
            System.out.println("Client updated");
        } catch (Exception e) {
            System.out.println("Error while updating client data");
            System.out.println(e.getMessage());
        }
    }

}
