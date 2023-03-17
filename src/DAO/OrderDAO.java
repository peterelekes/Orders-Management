package DAO;

import Model.Order;

import java.util.List;

public class OrderDAO extends AbstractDAO<Order> {
    /**
     * @return list of all orders
     */
    public List<Order> findAll() {
        return super.findAll();
    }

    /**
     * @param id
     * @return order with id
     */

    public Order findById(int id) {
        return super.findById(id);
    }

    /**
     * insert order
     * @param order
     */
    public void insertOrder(Order order) {
        super.insert(order);
    }

    /**
     * update order
     * @param order
     */
    public void updateOrder(Order order) {
        super.update(order);
    }

    /**
     * delete order
     * @param order
     */
    public void deleteOrder(Order order) {
        super.delete(order);
    }
}
