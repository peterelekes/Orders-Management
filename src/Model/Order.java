package Model;

import java.util.Date;

public class Order {
    private int id;
    private int quantity;
    private int clientId;
    private int productId;

    /**Default constructor*/
    public Order() {
    }

    /**Constructor with parameters
     * @param id id of the order
     * @param quantity quantity of the order
     * @param clientId id of the client
     * @param id_product id of the product
     */
    public Order(int id, int quantity, int clientId, int id_product) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.clientId = clientId;
        this.productId = id_product;
    }


    //region getters and setters

    /**
     * returns the id of the order
     */
    public int getId() {
        return id;
    }

    /**
     * returns the quantity of the order
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * returns the id of the client
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * returns the id of the product
     */
    public int getProductId() {
        return productId;
    }

    /**
     * sets the id of the order
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * sets the quantity of the order
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * sets the id of the client
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * sets the id of the product
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    //endregion
}