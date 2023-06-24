package com.tober.inventory.repository;

import com.tober.inventory.entity.Invoices;
import com.tober.inventory.util.HibernateUtil;
import com.tober.inventory.util.JPAUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ReportsRepository {

    private SessionFactory getSessionFactory() {
        return HibernateUtil.createSessionFactory();
    }

    private EntityManagerFactory entityManagerFactory() {
        return JPAUtil.createEntityManagerFactory();
    }

    public List<Invoices> loadCheckoutReport() {
        Session session = getSessionFactory().openSession();
        Query<Invoices> namedQuery = session.createNamedQuery("Invoices.findAll", Invoices.class);

        return namedQuery.getResultList();
    }
}
