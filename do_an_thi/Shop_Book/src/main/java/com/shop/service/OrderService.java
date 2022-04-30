package com.shop.service;

import com.shop.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderService {
    Page<Order> findAll(Pageable pageable);
    void saveOrder(Order order);
    void deleteOrder(int id);
    Optional<Order> findOrderById(int id);
    Page<Order> findOrderByCustomerNameContaining(String customerName,Pageable pageable);
}
