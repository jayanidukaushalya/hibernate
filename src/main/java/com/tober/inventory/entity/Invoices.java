package com.tober.inventory.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "invoices")
@NamedQuery(name = "Invoices.findAll", query = "SELECT i FROM Invoices i")
public class Invoices implements Serializable {

    @Id
    private Long id;

    @Column(name = "date_time", columnDefinition = "DATETIME")
    private Date date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "invoice_item",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    public Invoices() {
    }

    public Invoices(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
