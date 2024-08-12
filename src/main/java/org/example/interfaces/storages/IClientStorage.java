package org.example.interfaces.storages;

import lombok.NonNull;
import org.example.records.Client;

import java.util.List;
import java.util.Map;

public interface IClientStorage {
    /**
     * Returns all clients and their ids
     * @return all clients and their ids in the storage
     */
    public abstract @NonNull List<Map.Entry<Integer, Client>> getClients();

    /**
     * Returns client by its identifier
     * @param id clients identifier
     * @return client with same id
     */
    public abstract @NonNull Client getClientById(int id) throws Exception;

    /**
     * Adds client to the storage
     * @param client client to add
     */
    public abstract void addClient(@NonNull Client client);

    /**
     * Updates client in storage
     * @param clientId client identifier
     * @param client updated client
     */
    public abstract void updateClient(int clientId, @NonNull Client client) throws Exception;
}
