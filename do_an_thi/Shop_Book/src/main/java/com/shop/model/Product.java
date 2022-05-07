package com.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Long quantity;
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

}