package BLL;

import DAO.OrderDAO;
import Model.Client;
import Model.Order;
import Model.Product;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    private final OrderDAO orderDAO;

    public OrderBLL() {
        orderDAO = new OrderDAO();
    }


    /**
     * find order by id
     * @param id order id
     * @return order
     */
    public Order findOrderById(int id) {
        List<Order> orders = orderDAO.findAll();
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        JOptionPane.showMessageDialog(null, "No order with id " + id + " found");
        throw new NoSuchElementException("No order with id " + id);
    }

    /**
     * @return list of all orders
     */
    public List<Order> getOrders() {
        return orderDAO.findAll();
    }

    /**
     * add order to db
     * @param order order to be added
     */
    public void insertOrder(Order order) {
        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        List<Order> orders = orderDAO.findAll();
        Client client = clientBLL.findClientById(order.getClientId());
        Product product = productBLL.findProductById(order.getProductId());
        for(Order o : orders){
            if(o.getId() == order.getId()){
                JOptionPane.showMessageDialog(null, "Order already exists with id " + order.getId());
                throw new IllegalArgumentException("Order already exists with id " + order.getId());
            }
        }
        if(order.getQuantity() <= 0){
            JOptionPane.showMessageDialog(null, "Quantity must be greater than 0");
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if(order.getQuantity() > product.getOnStock()){
            JOptionPane.showMessageDialog(null, "Quantity must be less than or equal to " + product.getOnStock());
            throw new IllegalArgumentException("Quantity must be less than or equal to " + product.getOnStock());
        }
        Product newProduct = new Product(product.getId(), product.getName(), product.getPrice(), product.getOnStock() - order.getQuantity());
        productBLL.updateProduct(product.getId(), newProduct);
        orderDAO.insert(order);
        JOptionPane.showMessageDialog(null, "Order with id " + order.getId() + " added");
        CreateBill createBill = new CreateBill(order);
        createBill.createBill();
    }

    /**
     * delete order from db by id
     * @param id order id
     */
    public void deleteOrder(int id) {
        Order order = findOrderById(id);
        orderDAO.delete(order);
        JOptionPane.showMessageDialog(null, "Order with id " + id + " deleted");
    }

    /**
     * updates an order by id
     * @param id order id
     * @param newOrder new order
     */
    public void updateOrder(int id, Order newOrder) {
        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        Order order = findOrderById(id);
        int idNew = order.getId();
        order = new Order(idNew, newOrder.getQuantity(), newOrder.getClientId(), newOrder.getProductId());
        if(order.getQuantity() <= 0){
            JOptionPane.showMessageDialog(null, "Quantity must be greater than 0");
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if(order.getQuantity() > productBLL.findProductById(order.getProductId()).getOnStock()){
            JOptionPane.showMessageDialog(null, "Quantity must be less than or equal to " + productBLL.findProductById(order.getProductId()).getOnStock());
            throw new IllegalArgumentException("Quantity must be less than or equal to " + productBLL.findProductById(order.getProductId()).getOnStock());
        }
        clientBLL.findClientById(order.getClientId());
        Product product = productBLL.findProductById(order.getProductId());
        Product newProduct = new Product(product.getId(), product.getName(), product.getPrice(), product.getOnStock() - order.getQuantity());
        productBLL.updateProduct(product.getId(), newProduct);
        orderDAO.update(order);
        JOptionPane.showMessageDialog(null, "Order with id " + order.getId() + " updated");
        CreateBill createBill = new CreateBill(order);
        createBill.createBill();
    }


}