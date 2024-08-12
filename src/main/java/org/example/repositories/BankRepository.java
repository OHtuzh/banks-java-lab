package org.example.repositories;

import org.example.interfaces.storages.IBankStorage;
import org.example.records.Bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankRepository implements IBankStorage {
    /**
     * Bank storage itself
     */
    protected static HashMap<Integer, Bank> bankStorage = new HashMap<>();
    /**
     * Identifier of the next bank
     */
    protected static int nextBankId = 0;

    /**
     * Returns bank by its id
     *
     * @param id bankId
     * @return Bank
     */
    @Override
    public Bank getBankById(int id) throws Exception {
        var bank = bankStorage.get(id);
        if (bank == null) {
            throw new Exception("There's no such bank!");
        }
        return bank;
    }

    /**
     * Adds bank to the storage
     *
     * @param bank some bank
     */
    @Override
    public void addBank(Bank bank) {
        bankStorage.put(nextBankId, bank);
        nextBankId++;
    }

    /**
     * Returns all banks in the storage
     *
     * @return all banks in the storage
     */
    @Override
    public List<Map.Entry<Integer, Bank>> getAllBanks() {
        return bankStorage.entrySet().stream().toList();
    }
}
