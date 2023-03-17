package BLL;

import DAO.ProductDAO;
import Model.Product;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private final ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * find product by id
     *
     * @param id of product
     * @return product
     */
    public Product findProductById(int id) {
        List<Product> products = productDAO.findAll();
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        JOptionPane.showMessageDialog(null, "No product with id " + id + " found");
        throw new NoSuchElementException("The product with id =" + id + " was not found!");
    }

    /**
     * @return list of all products
     */
    public List<Product> getProducts() {
        return productDAO.findAll();
    }

    /**
     * add product to db
     *
     * @param product to be added
     */
    public void insertProduct(Product product) {
        List<Product> products = productDAO.findAll();
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                JOptionPane.showMessageDialog(null, "Product with id " + product.getId() + " already exists");
                throw new IllegalArgumentException("Product with id " + product.getId() + " already exists");
            }
        }
        productDAO.insert(product);
        JOptionPane.showMessageDialog(null, "Product with id " + product.getId() + " added");
    }

    /**
     * delete product from db by id
     *
     * @param id of product
     */
    public void deleteProduct(int id) {
        Product product = findProductById(id);
        productDAO.delete(product);
        JOptionPane.showMessageDialog(null, "Product with id " + id + " deleted");
    }

    /**
     * updates a product by id
     *
     * @param id of product
     * @param newProduct with new values
     * @return product with new values
     */
    public void updateProduct(int id, Product newProduct) {
        Product product = findProductById(id);
        int idNew = product.getId();
        product = new Product(idNew, newProduct.getName(), newProduct.getPrice(), newProduct.getOnStock());
        productDAO.update(product);
        JOptionPane.showMessageDialog(null, "Product with id " + id + " updated");
    }
}