package org.example.repositories;

import lombok.NonNull;
import org.example.interfaces.storages.IClientStorage;
import org.example.records.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientRepository implements IClientStorage {
    /**
     * Client storage itself
     */
    protected static HashMap<Integer, Client> clientStorage = new HashMap<>();
    /**
     * Identifier of the next client
     */
    protected static int nextClientId = 0;

    /**
     * Returns all clients and their ids
     *
     * @return all clients and their ids in the storage
     */
    @Override
    public @NonNull List<Map.Entry<Integer, Client>> getClients() {
        return clientStorage.entrySet().stream().toList();
    }

    /**
     * Returns client by its identifier
     *
     * @param id clients identifier
     * @return client with same id
     */
    @Override
    public @NonNull Client getClientById(int id) throws Exception {
        var client = clientStorage.get(id);
        if (client == null) {
            throw new Exception("There's no such client");
        }
        return client;
    }

    /**
     * Adds client to the storage
     *
     * @param client client to add
     */
    @Override
    public void addClient(@NonNull Client client) {
        clientStorage.put(nextClientId, client);
        nextClientId++;
    }

    /**
     * Updates client in storage
     *
     * @param clientId client identifier
     * @param client   updated client
     */
    @Override
    public void updateClient(int clientId, @NonNull Client client) throws Exception {
        if (!clientStorage.containsKey(clientId)) {
            throw new Exception("There's no such client");
        }
        clientStorage.put(clientId, client);
    }
}
