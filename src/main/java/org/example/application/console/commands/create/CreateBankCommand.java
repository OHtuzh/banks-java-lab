package org.example.application.console.commands.create;

import org.example.application.interfaces.IApplicationEngine;
import org.example.application.console.commands.CommandBase;
import org.example.records.Bank;

import java.util.Scanner;

public class CreateBankCommand extends CommandBase {

    public CreateBankCommand(IApplicationEngine applicationEngine) {
        super(applicationEngine);
    }

    @Override
    public void execute() {
        var scanner = new Scanner(System.in);
        System.out.print("Enter Bank Name: ");
        String bankName = scanner.nextLine();
        if (bankName.isEmpty()) {
            System.out.println("Bank name cannot be empty");
            return;
        }

        double interestRate;
        try {
            System.out.print("Enter Interest Rate: ");
            interestRate = scanner.nextDouble();
        } catch (Exception exception) {
            System.out.println("Invalid interest rate");
            return;
        }

        double commission;
        try {
            System.out.print("Enter Commission Rate: ");
            commission = scanner.nextDouble();
        } catch (Exception exception) {
            System.out.println("Invalid Commission Rate");
            return;
        }

        int creditLimit;
        try {
            System.out.print("Enter Credit Limit: ");
            creditLimit = scanner.nextInt();
        } catch (Exception exception) {
            System.out.println("Invalid Credit Limit");
            return;
        }

        int maxUnapprovedClientTransaction;
        try {
            System.out.print("Enter Max Unapproved Client Transaction: ");
            maxUnapprovedClientTransaction = scanner.nextInt();
        } catch (Exception exception) {
            System.out.println("Invalid Max Unapproved Client Transaction");
            return;
        }

        try {
            applicationEngine.createBank(new Bank(bankName, interestRate, commission, creditLimit, maxUnapprovedClientTransaction));
            System.out.println("Bank created");
        } catch (Exception e) {
            System.out.println("Bank creation failed");
            System.out.println(e.getMessage());
        }
    }

}
