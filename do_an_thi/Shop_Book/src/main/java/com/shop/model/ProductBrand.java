package com.shop.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ProductBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String status;
    @OneToMany(mappedBy = "id")
    private Set<Product> products;

    public ProductBrand() {
    }

    public ProductBrand(int id, String name, String status, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}