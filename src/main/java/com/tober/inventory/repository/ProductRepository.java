package com.tober.inventory.repository;

import com.tober.inventory.entity.Product;
import com.tober.inventory.util.HibernateUtil;
import com.tober.inventory.util.JPAUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductRepository {

    private SessionFactory getSessionFactory() {
        return HibernateUtil.createSessionFactory();
    }

    private EntityManagerFactory entityManagerFactory() {
        return JPAUtil.createEntityManagerFactory();
    }

    public void addProduct(String name, Double price, Integer status) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Product product = new Product(name, price, status);

            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Product> getAllProducts() {
        Session session = getSessionFactory().openSession();
        Query query = session.createNamedQuery("Product.findAll");
        List<Product> resultList = query.getResultList();

        return resultList;
    }
}
