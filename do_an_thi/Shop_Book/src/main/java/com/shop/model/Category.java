package com.shop.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private int id;
    @Column(name = "name")
    private String name;
    private String status;
    @OneToMany(mappedBy = "id")
    private Set<Product> products;
    public Category() {
    }
    public Category(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
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

    public void setDescription(String description) {
        this.status = status;
    }


    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
