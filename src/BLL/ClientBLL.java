package BLL;

import BLL.Validators.EmailValidator;
import BLL.Validators.NameValidator;
import DAO.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private final ClientDAO clientDAO;
    private final EmailValidator emailValidator = new EmailValidator();

    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /**
     * find client by id
     *
     * @param id
     * @return client
     */
    public Client findClientById(int id) {
        List<Client> clients = clientDAO.findAll();
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        JOptionPane.showMessageDialog(null, "Client with id " + id + " not found");
        throw new NoSuchElementException("The client with id =" + id + " was not found!");
    }

    /**
     * @return list of all clients
     */
    public List<Client> getClients() {
        return clientDAO.findAll();
    }

    /**
     * add client to db
     *
     * @param client
     */
    public void insertClient(Client client) {
        List<Client> clients = clientDAO.findAll();
        for (Client client1 : clients) {
            if (client1.getId() == client.getId()) {
                JOptionPane.showMessageDialog(null, "Client with id " + client.getId() + " already exists");
                throw new IllegalArgumentException("Client with id " + client.getId() + " already exists");
            }
        }
        clientDAO.insert(client);
        JOptionPane.showMessageDialog(null, "Client with id " + client.getId() + " added");
    }

    /**
     * delete client from db by id
     *
     * @param id
     */
    public void deleteClient(int id) {
        Client client = findClientById(id);
        clientDAO.delete(client);
        JOptionPane.showMessageDialog(null, "Client with id " + id + " deleted");
    }

    /**
     * updates a client by id
     *
     * @param id
     * @param newClient
     * @return client
     */
    public void updateClient(int id, Client newClient) {
        Client client = findClientById(id);
        int idNew = client.getId();
        client = new Client(idNew, newClient.getName(), newClient.getAddress(), newClient.getEmail());
        clientDAO.update(client);
        JOptionPane.showMessageDialog(null, "Client with id " + id + " updated");
    }

    /**
     * get email validator
     *
     * @return email validator
     */
    public EmailValidator getEmailValidator() {
        return emailValidator;
    }

    /**
     * get name validator
     *
     * @return name validator
     */
    public NameValidator getNameValidator() {
        return new NameValidator();
    }

}