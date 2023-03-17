package Presentation;

import BLL.ProductBLL;
import Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductView {
    private JPanel productPanel;
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
    ProductTable productTable;

    public ProductView() {
        frame = new JFrame("Product");
        frame.setContentPane(productPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
        showTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productTable = new ProductTable();
            }
        });
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=0;
                String name=null;
                int price=0;
                int onStock=0;
                if(textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("") || textField4.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }
                try {
                    id = Integer.parseInt(textField1.getText());
                    price = Integer.parseInt(textField3.getText());
                    onStock = Integer.parseInt(textField4.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please insert a valid number");
                    return;
                }
                if(id<0 || price<0 || onStock<0){
                    JOptionPane.showMessageDialog(null, "Please insert a valid number");
                    return;
                }
                name=textField2.getText();
                Product product = new Product(id,name,price,onStock);
                ProductBLL productBLL = new ProductBLL();
                productBLL.insertProduct(product);
                productTable.updateTable();
            }
        });
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
                ProductBLL productBLL = new ProductBLL();
                productBLL.deleteProduct(id);
                productTable.updateTable();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=0;
                String name=null;
                int price=0;
                int onStock=0;
                if(textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("") || textField4.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }
                try {
                    id = Integer.parseInt(textField1.getText());
                    price = Integer.parseInt(textField3.getText());
                    onStock = Integer.parseInt(textField4.getText());
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please insert a valid id");
                    return;
                }
                if(id<0 || price<0 || onStock<0){
                    JOptionPane.showMessageDialog(null, "Please insert a valid number");
                    return;
                }
                name=textField2.getText();
                Product product = new Product(id,name,price,onStock);
                ProductBLL productBLL = new ProductBLL();
                productBLL.updateProduct(id, product);
                productTable.updateTable();
            }
        });
    }
}
