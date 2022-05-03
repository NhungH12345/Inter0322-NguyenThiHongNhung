package com.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private int id;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name = "customer_address")
    private String customerAddress;
    private String phone;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "id")
    private Set<OrderDetail> orderDetails;
    @Column(name = "method_payment")
    private Integer methodPayment;
}