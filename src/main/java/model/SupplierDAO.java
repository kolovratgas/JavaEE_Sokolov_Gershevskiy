package model;

import model.entity.Supplier;

import java.util.List;

public interface SupplierDAO {

    List<Supplier> suppliers();

    boolean createSupplier(Supplier supplier);

    boolean readSupplier(Supplier supplier);

    boolean updateSupplier(Supplier supplier);

    boolean deleteSupplier(Supplier supplier);

}
