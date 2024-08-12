package org.example.records;

import org.example.enums.EAccountType;

public record Account(int clientId, int bankId, int balance, EAccountType accountType) {
}
