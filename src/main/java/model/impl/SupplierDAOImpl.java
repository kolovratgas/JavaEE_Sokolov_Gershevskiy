package model.impl;

import model.SupplierDAO;
import model.entity.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    private static final EntityManager entityManager = Persistence.createEntityManagerFactory("public").createEntityManager();

    @Transactional
    @Override
    public List<Supplier> suppliers() {
        return entityManager.createQuery("SELECT s FROM supplier s").getResultList();
    }

    @Transactional
    @Override
    public boolean createSupplier(Supplier supplier) {
        System.out.println("============");
        supplier.setId((long) 3);
        System.out.println(supplier.toString());
        entityManager.getTransaction().begin();
        entityManager.persist(supplier);
        entityManager.getTransaction().commit();
        return true;
    }

    @Transactional
    @Override
    public boolean readSupplier(Supplier supplier) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT s FROM supplier s WHERE s.id = :id");
        query.setParameter("id", supplier.getId());
        entityManager.getTransaction().commit();
        return true;
    }

    @Transactional
    @Override
    public boolean updateSupplier(Supplier supplier) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE supplier s SET s.name = :name, s.INN = :inn, s.address = :address, s.phone = :phone "
                + "WHERE s.id = :id");
        query.setParameter("id", supplier.getId());
        query.setParameter("name", supplier.getName());
        query.setParameter("inn", supplier.getINN());
        query.setParameter("address", supplier.getAddress());
        query.setParameter("phone", supplier.getPhone());
        entityManager.getTransaction().commit();
        return true;
    }

    @Transactional
    @Override
    public boolean deleteSupplier(Supplier supplier) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM supplier s WHERE s.id = :id");
        query.setParameter("id", supplier.getId());
        entityManager.getTransaction().commit();
        return true;
    }

}