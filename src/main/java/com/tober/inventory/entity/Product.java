package com.tober.inventory.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@NamedQuery(
        name = "Product.findAll",
        query = "SELECT p from Product p"
)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    private Integer status;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    @Transient
    private Set<Invoices> invoices = new HashSet<>();

    public Product() {
    }

    public Product(String name, Double price, Integer status) {
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Invoices> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoices> invoices) {
        this.invoices = invoices;
    }
}
