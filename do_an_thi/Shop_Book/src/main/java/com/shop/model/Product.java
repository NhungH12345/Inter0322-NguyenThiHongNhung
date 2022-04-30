package com.shop.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String quantity;
    private String avatar;
    private double price;
    private String status;
    private String description;
    @ManyToOne(targetEntity = Category.class)
    private Category category;
    @ManyToOne(targetEntity = ProductBrand.class)
    private ProductBrand productBrand;
    @OneToMany(mappedBy = "id")
    private Set<OrderDetail> orderDetails;

    public Product() {
    }

    public Product(int id, String name, String quantity, String avatar, double price, String status, String description, Category category, ProductBrand productBrand, Set<OrderDetail> orderDetails) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.avatar = avatar;
        this.price = price;
        this.status = status;
        this.description = description;
        this.category = category;
        this.productBrand = productBrand;
        this.orderDetails = orderDetails;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductBrand getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(ProductBrand productBrand) {
        this.productBrand = productBrand;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}