package DAO;

import Model.Client;

import java.util.List;

public class ClientDAO extends AbstractDAO<Client> {
    /**
     * @return list of all clients
     */
    public List<Client> findAll() {
        return super.findAll();
    }

    /**
     * @param id
     * @return client with id
     */

    public Client findById(int id) {
        return super.findById(id);
    }

    /**
     * insert client
     * @param client
     */
    public void insertClient(Client client) {
        super.insert(client);
    }

    /**
     * update client
     * @param client
     */
    public void updateClient(Client client) {
        super.update(client);
    }

    /**
     * delete client
     * @param client
     */
    public void deleteClient(Client client) {
        super.delete(client);
    }
}