package managedBeans;

import model.ProductDAO;
import model.entity.Product;
import model.impl.ProductDAOImpl;
import org.primefaces.PrimeFaces;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@Stateless
public class ProductManagedBean {
    private ProductDAO dao;

    public List<Product> products;
    public Product current;

    public ProductManagedBean() {
        this.dao = new ProductDAOImpl();
        this.products = dao.products();
    }

    public List<Product> products() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getCurrent() {
        return current;
    }

    public void setCurrent(Product current) {
        this.current = current;
    }

    public void acceptEditProduct() {
        dao.updateProduct(current);
        this.products = dao.products();
        PrimeFaces.current().executeScript("PF('editDialog').hide()");
    }

    public void addNewProduct() {
        this.current = new Product();
    }

    public void acceptNewProduct() {
        dao.createProduct(current);
        this.products = dao.products();
        PrimeFaces.current().executeScript("PF('addDialog').hide()");
    }

    public void removeProduct(Product product) {
        dao.deleteProduct(product);
        this.products = dao.products();
    }

    public String goToProduct() {
        return "product";
    }

    public String goToSupplier() {
        return "supplier";
    }

}
