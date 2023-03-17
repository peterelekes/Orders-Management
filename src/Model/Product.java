package Model;

public class Product {
    private int id;
    private String name;
    private int price;
    private int onStock;

    /**Default constructor*/
    public Product() {
    }

    /**Constructor with parameters
     * @param id id of a product
     * @param name name of a product
     * @param price price of a product
     * @param onStock stock of a product
     */
    public Product(int id, String name, int price, int onStock) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.onStock = onStock;
    }

    //region getters and setters

    /**
     * returns the id of a product
     */
    public int getId() {
        return id;
    }

    /**
     * returns the name of a product
     */
    public String getName() {
        return name;
    }

    /**
     * returns the price of a product
     */
    public int getPrice() {
        return price;
    }

    /**
     * returns the stock of a product
     */
    public int getOnStock() {
        return onStock;
    }

    /**
     * sets the id of a product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * sets the name of a product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the price of a product
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * sets the stock of a product
     */
    public void setOnStock(int stock) {
        this.onStock = stock;
    }

    /**
     * constructs a string representation of a product
     */
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + onStock + "]";
    }

    //endregion
}