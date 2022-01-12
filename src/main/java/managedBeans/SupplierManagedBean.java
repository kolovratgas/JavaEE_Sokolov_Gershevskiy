package managedBeans;

import model.SupplierDAO;
import model.entity.Supplier;
import model.impl.SupplierDAOImpl;
import org.primefaces.PrimeFaces;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@Stateless
public class SupplierManagedBean {
    private SupplierDAO dao;

    List<Supplier> suppliers;
    Supplier current;

    public SupplierManagedBean() {
        this.dao = new SupplierDAOImpl();
        this.suppliers = dao.suppliers();
    }

    public List<Supplier> suppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public Supplier getCurrent() {
        return current;
    }

    public void setCurrent(Supplier current) {
        this.current = current;
    }

    public void acceptEditSupplier() {
        dao.updateSupplier(current);
        this.suppliers = dao.suppliers();
        PrimeFaces.current().executeScript("PF('editDialog').hide()");
    }

    public void addNewSupplier() {
        this.current = new Supplier();
    }

    public void acceptNewSupplier() {
        dao.createSupplier(current);
        this.suppliers = dao.suppliers();
        PrimeFaces.current().executeScript("PF('addDialog').hide()");
    }

    public void removeSupplier(Supplier supplier) {
        dao.deleteSupplier(supplier);
        this.suppliers = dao.suppliers();
    }

    public String goToProduct() {
        return "product";
    }

    public String goToSupplier() {
        return "supplier";
    }

}
