package org.example.interfaces.storages;

import org.example.records.Bank;

import java.util.List;
import java.util.Map;

public interface IBankStorage {
    /**
     * Returns bank by its id
     * @param id bankId
     * @return Bank
     */
    public abstract Bank getBankById(int id) throws Exception;

    /**
     * Adds bank to the storage
     * @param bank some bank
     */
    public abstract void addBank(Bank bank);

    /**
     * Returns all banks in the storage
     * @return all banks in the storage
     */
    public abstract List<Map.Entry<Integer, Bank>> getAllBanks();
}
