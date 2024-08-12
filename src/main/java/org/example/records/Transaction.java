package org.example.records;

import org.example.enums.ETransactionType;

public record Transaction(int fromAccountId, int toAccountId, int amount, ETransactionType transactionType, boolean canceled) {
}
