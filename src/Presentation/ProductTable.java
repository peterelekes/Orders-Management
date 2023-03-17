package Presentation;

import BLL.ProductBLL;
import Model.Product;

import javax.swing.*;
import java.util.List;

public class ProductTable extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel panel;
    private List<Product> products;

    /**
     * Constructor for the Table
     */
    public ProductTable() {
        setTitle("Product Table");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        updateTable();
    }

    public void updateTable() {
        ProductBLL productBLL = new ProductBLL();
        products = productBLL.getProducts();
        String[] columns = {"ID", "Name", "Price","Quantity in stock"};
        Object[][] data = new Object[products.size()][4];
        for (int i = 0; i < products.size(); i++) {
            data[i][0] = products.get(i).getId();
            data[i][1] = products.get(i).getName();
            data[i][2] = products.get(i).getPrice();
            data[i][3] = products.get(i).getOnStock();
        }
        table = new JTable(data, columns);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(table);
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
