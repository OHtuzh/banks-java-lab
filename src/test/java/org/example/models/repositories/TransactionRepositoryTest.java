package org.example.models.repositories;

import org.example.records.Transaction;
import org.example.enums.ETransactionType;
import org.example.repositories.TransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.AbstractMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TransactionRepositoryTest {
    private final TransactionRepository storage = new TransactionRepository();

    @BeforeEach
    void setUp() {
        storage.addTransaction(new Transaction(1, 2, 100, ETransactionType.TRANSFER, false));
        storage.addTransaction(new Transaction(3, 1, 200, ETransactionType.TRANSFER, false));
        storage.addTransaction(new Transaction(0, 1, 300, ETransactionType.INTEREST, false));
    }

    @AfterEach
    void tearDown() {
        storage.dropStorage();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void getTransactionById(int transactionId) {
        final Transaction[] expected = new Transaction[]{
                new Transaction(1, 2, 100, ETransactionType.TRANSFER, false),
                new Transaction(3, 1, 200, ETransactionType.TRANSFER, false),
                new Transaction(0, 1, 300, ETransactionType.INTEREST, false)
        };
        assertDoesNotThrow(() -> assertEquals(storage.getTransactionById(transactionId), expected[transactionId]));
    }

    @Test
    void getAllTransactions() {
        assertArrayEquals(
                storage.getAllTransactions().toArray(),
                new Map.Entry[]{
                        new AbstractMap.SimpleEntry<Integer,Transaction>(0, new Transaction(1, 2, 100, ETransactionType.TRANSFER, false)),
                        new AbstractMap.SimpleEntry<Integer,Transaction>(1, new Transaction(3, 1, 200, ETransactionType.TRANSFER, false)),
                        new AbstractMap.SimpleEntry<Integer,Transaction>(2, new Transaction(0, 1, 300, ETransactionType.INTEREST, false))
                }
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void addTransaction(int transactionId) {
        // Ids were added in setUp, so I'm checking their presents
        assertDoesNotThrow(() -> storage.getTransactionById(transactionId));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void removeTransaction(int transactionId) {
        assertDoesNotThrow(() -> storage.removeTransaction(transactionId));
        assertThrows(Exception.class, () -> storage.getTransactionById(transactionId));
    }

    @Test
    void dropStorage() {
        storage.dropStorage();
        assertTrue(storage.getAllTransactions().isEmpty());
    }
}