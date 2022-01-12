package model;

import model.entity.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> products();

    boolean createProduct(Product product);

    boolean readProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(Product product);

}
