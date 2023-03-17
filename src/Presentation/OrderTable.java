package Presentation;

import BLL.OrderBLL;
import Model.Order;

import javax.swing.*;
import java.util.List;

public class OrderTable extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel panel;
    private List<Order> orders;

    /**
     * Constructor for the Table
     */
    public OrderTable() {
        setTitle("Order Table");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        updateTable();
    }
    //update table
    public void updateTable() {
        OrderBLL orderBLL = new OrderBLL();
        orders = orderBLL.getOrders();
        String[] columns = {"ID", "Quantity", "Client ID", "Product ID"};
        Object[][] data = new Object[orders.size()][4];
        for (int i = 0; i < orders.size(); i++) {
            data[i][0] = orders.get(i).getId();
            data[i][1] = orders.get(i).getQuantity();
            data[i][2] = orders.get(i).getClientId();
            data[i][3] = orders.get(i).getProductId();
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
