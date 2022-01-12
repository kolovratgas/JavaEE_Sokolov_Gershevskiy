package model.impl;

import model.ProductDAO;
import model.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private static final EntityManager entityManager = Persistence.createEntityManagerFactory("public").createEntityManager();

    @Transactional
    @Override
    public List<Product> products() {
        return entityManager.createQuery("SELECT p FROM product p").getResultList();
    }

    @Transactional
    @Override
    public boolean createProduct(Product product) {
        System.out.println("============");
        product.setId((long) 3);
        System.out.println(product.toString());
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        return true;
    }

    @Transactional
    @Override
    public boolean readProduct(Product product) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT p FROM product p WHERE p.id = :id");
        query.setParameter("id", product.getId());
        entityManager.getTransaction().commit();
        return true;
    }

    @Transactional
    @Override
    public boolean updateProduct(Product product) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE product a SET a.name = :name, a.code = :code, a.price = :price "
                + "WHERE a.id = :id");
        query.setParameter("id", product.getId());
        query.setParameter("name", product.getName());
        query.setParameter("code", product.getCode());
        query.setParameter("price", product.getPrice());
        entityManager.getTransaction().commit();
        return true;
    }

    @Transactional
    @Override
    public boolean deleteProduct(Product product) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM product a WHERE a.id = :id");
        query.setParameter("id", product.getId());
        entityManager.getTransaction().commit();
        return true;
    }

}