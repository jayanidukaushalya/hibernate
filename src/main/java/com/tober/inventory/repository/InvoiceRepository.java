package com.tober.inventory.repository;

import com.tober.inventory.entity.Invoices;
import com.tober.inventory.entity.Product;
import com.tober.inventory.util.HibernateUtil;
import com.tober.inventory.util.JPAUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InvoiceRepository {

    private SessionFactory getSessionFactory() {
        return HibernateUtil.createSessionFactory();
    }

    private EntityManagerFactory entityManagerFactory() {
        return JPAUtil.createEntityManagerFactory();
    }

    public List<Product> getSearchItemList(String text) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("SELECT p FROM Product p WHERE name LIKE :text");
        query.setParameter("text", "%"+text+"%");

        return query.getResultList();
    }

    public Product getItemUsingId(String id) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("SELECT p FROM Product p WHERE id = :id");
        query.setParameter("id", Long.parseLong(id));

        return (Product) query.getSingleResult();
    }

    public void insertCheckoutData(String[] ids) {

        long[] digits = new long[ids.length];

        for (int i = 0; i < ids.length; i++) {
            digits[i] = Long.parseLong(ids[i]);
        }

        Session session = getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = sdf.format(new Date());
            Date date = sdf.parse(formattedDate);

            long id = System.currentTimeMillis();

            Invoices invoices = new Invoices(id, date);

            Query query = session.createQuery("SELECT p FROM Product p WHERE id = :id");
            for (int i = 0; i < digits.length; i++) {
                query.setParameter("id", digits[i]);

                Product product = (Product) query.getSingleResult();
                invoices.getProducts().add(product);
            }

            session.save(invoices);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
