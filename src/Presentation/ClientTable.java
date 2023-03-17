package Presentation;

import BLL.ClientBLL;
import Model.Client;

import javax.swing.*;
import java.util.List;

public class ClientTable extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel panel;
    private List<Client> clients;

    /**
     * Constructor for the Table
     */
    public ClientTable() {
        setTitle("Client Table");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        updateTable();
    }

    /**
     * Updates the table
     * @return void*/
    public void updateTable() {
        ClientBLL clientBLL = new ClientBLL();
        clients = clientBLL.getClients();
        String[] columns = {"Id", "Name", "Address", "Email"};
        Object[][] data = new Object[clients.size()][4];
        for (int i = 0; i < clients.size(); i++) {
            data[i][0] = clients.get(i).getId();
            data[i][1] = clients.get(i).getName();
            data[i][2] = clients.get(i).getAddress();
            data[i][3] = clients.get(i).getEmail();
        }
        table = new JTable(data, columns);
        scrollPane = new JScrollPane(table);
        panel = new JPanel();
        panel.add(scrollPane);
        add(panel);
        setVisible(true);
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JTable getTable() {
        return table;
    }

}
