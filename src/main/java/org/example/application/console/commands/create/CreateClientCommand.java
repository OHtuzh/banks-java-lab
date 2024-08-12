package org.example.application.console.commands.create;

import org.example.application.interfaces.IApplicationEngine;
import org.example.application.console.commands.CommandBase;
import org.example.records.Client;

import java.util.Scanner;

public class CreateClientCommand extends CommandBase {

    public CreateClientCommand(IApplicationEngine applicationEngine) {
        super(applicationEngine);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        if (firstName.isEmpty()) {
            System.out.println("Name cannot be empty");
            return;
        }

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        if (lastName.isEmpty()) {
            System.out.println("Last name cannot be empty");
            return;
        }

        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        if (address.isEmpty()) {
            System.out.println("Address is empty. It is ok, but you will have some restrictions.");
        }

        System.out.print("Enter passport: ");
        String passport = scanner.nextLine();
        if (passport.isEmpty()) {
            System.out.println("Passport is empty. It is ok, but you will have some restrictions.");
        }

        try {
            applicationEngine.createClient(new Client(firstName, lastName, address, passport));
            System.out.println("Client created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
