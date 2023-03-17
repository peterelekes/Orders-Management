package DAO;

import Model.Client;
import Model.Product;

import java.util.List;

public class ProductDAO extends AbstractDAO<Product>{
    /**
     * @return list of all products
     */
    public List<Product> findAll() {
        return super.findAll();
    }

    /**
     * @param id
     * @return product with id
     */

    public Product findById(int id) {
        return super.findById(id);
    }

    /**
     * insert product
     * @param product
     */
    public void insert(Product product) {
        super.insert(product);
    }

    /**
     * update product
     * @param product
     */
    public void update(Product product) {
        super.update(product);
    }

    /**
     * delete product
     * @param product
     */
    public void delete(Product product) {
        super.delete(product);
    }
}
