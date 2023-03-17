package Presentation;

import BLL.ClientBLL;
import DAO.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientView {
    private JPanel clientPanel;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JLabel Title;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton showTableButton;
    JFrame frame;
    ClientTable clientTable;

    /**
     * Constructor for the class ClientView
     */
    public ClientView() {
        frame = new JFrame("Client");
        frame.setContentPane(clientPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
        /**
         * ActionListener for the Show Table button
         * @param e
         */
        showTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientTable = new ClientTable();
            }
        });
        /**
         * ActionListener for the Insert button
         * Validates the input and inserts the client in the database
         * @param e
         */
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=0;
                String name=null;
                String address=null;
                String email=null;
                if(textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("") || textField4.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }
                try {
                    id = Integer.parseInt(textField1.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please insert a valid id");
                    return;
                }
                name=textField2.getText();
                address=textField3.getText();
                email=textField4.getText();
                ClientBLL clientBLL = new ClientBLL();
                Client client = new Client(id, name, address, email);
                clientBLL.getEmailValidator().validate(client);
                clientBLL.getNameValidator().validate(client);
                clientBLL.insertClient(client);
                clientTable.updateTable();
            }
        });
        /**
         * ActionListener for the Delete button
         * Validates the input and deletes the client from the database
         * @param e
         */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=0;
                if(textField1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please insert an id");
                    return;
                }
                try {
                    id = Integer.parseInt(textField1.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please insert a valid id");
                    return;
                }
                ClientBLL clientBLL = new ClientBLL();
                clientBLL.deleteClient(id);
                clientTable.updateTable();
            }
        });
        /**
         * ActionListener for the Update button
         * Validates the input and updates the client in the database
         * @param e
         */
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=0;
                String name=null;
                String address=null;
                String email=null;
                if(textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("") || textField4.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }
                try {
                    id = Integer.parseInt(textField1.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please insert a valid id");
                    return;
                }
                name=textField2.getText();
                address=textField3.getText();
                email=textField4.getText();
                Client client = new Client(id, name, address, email);
                ClientBLL clientBLL = new ClientBLL();
                clientBLL.getEmailValidator().validate(client);
                clientBLL.getNameValidator().validate(client);
                clientBLL.updateClient(id, client);
                clientTable.updateTable();
            }
        });
    }
}
