package BLL;

import Model.Order;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class CreateBill {
    Order order;

    /**
     * Constructor for the class CreateBill
     * @param order the order to be created
     */
    public CreateBill(Order order) {
        this.order = order;
    }

    /**
     * Creates a bill for the order
     * @throws IOException if the file cannot be created
     */
    public void createBill(){
        ProductBLL productBLL = new ProductBLL();
        ClientBLL clientBLL = new ClientBLL();
        File file = new File("bills/bill_id_" + order.getId() + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("***************************\n");
            writer.write("Bill id: " + order.getId() + "\n");
            writer.write("***************************\n");
            writer.write("Customer name: " + clientBLL.findClientById(order.getClientId()).getName() + "\n");
            writer.write("Customer address: " + clientBLL.findClientById(order.getClientId()).getAddress() + "\n");
            writer.write("Customer email: " + clientBLL.findClientById(order.getClientId()).getEmail() + "\n");
            writer.write("***************************\n");
            writer.write("Product: " + productBLL.findProductById(order.getProductId()).getName() + "\n");
            writer.write("Product price: " + productBLL.findProductById(order.getProductId()).getPrice() + "\n");
            writer.write("Product quantity: " + order.getQuantity() + "\n");
            writer.write("***************************\n");
            writer.write("Total price: " + productBLL.findProductById(order.getProductId()).getPrice() * order.getQuantity() + "\n");
            writer.write("***************************\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
