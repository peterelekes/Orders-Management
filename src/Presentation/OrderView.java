package Presentation;

import BLL.OrderBLL;
import Model.Order;
import Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderView {
    private JPanel orderPanel;
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
    OrderTable orderTable;

    /**
     * Constructor for the order view
     */
    public OrderView() {
        frame = new JFrame("Order");
        frame.setContentPane(orderPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
        /**
         * Table button action listener
         * Opens the table view
         * @param e
         */
        showTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderTable = new OrderTable();
            }
        });
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = 0;
                int quantity = 0;
                int clientId = 0;
                int productId = 0;
                if (textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("") || textField4.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }
                try {
                    id = Integer.parseInt(textField1.getText());
                    quantity = Integer.parseInt(textField2.getText());
                    clientId = Integer.parseInt(textField3.getText());
                    productId = Integer.parseInt(textField4.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please insert a valid number");
                    return;
                }
                Order order = new Order(id, quantity, clientId, productId);
                OrderBLL orderBLL = new OrderBLL();
                orderBLL.insertOrder(order);
                orderTable.updateTable();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = 0;
                if (textField1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please insert an id");
                    return;
                }
                try {
                    id = Integer.parseInt(textField1.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please insert a valid id");
                    return;
                }
                OrderBLL orderBLL = new OrderBLL();
                orderBLL.deleteOrder(id);
                orderTable.updateTable();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = 0;
                int quantity = 0;
                int clientId = 0;
                int productId = 0;
                if (textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("") || textField4.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }
                try {
                    id = Integer.parseInt(textField1.getText());
                    quantity = Integer.parseInt(textField2.getText());
                    clientId = Integer.parseInt(textField3.getText());
                    productId = Integer.parseInt(textField4.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please insert a valid id");
                    return;
                }
                Order order = new Order(id, quantity, clientId, productId);
                OrderBLL orderBLL = new OrderBLL();
                orderBLL.updateOrder(id,order);
                orderTable.updateTable();
            }
        });
    }
}
