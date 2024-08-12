package org.example.records;

public record Bank(String name, double interestRate, double commission, int creditLimit, int maxUnapprovedClientTransaction) {
}
