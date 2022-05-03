package com.shop.service;

import com.shop.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderService {
    Page<Order> findAll(Pageable pageable);
    Order saveOrder(Order order);
    void deleteOrder(int id);
    Optional<Order> findOrderById(int id);
    Page<Order> findOrderByCustomerNameContaining(String customerName,Pageable pageable);
    Page<Order> findByEmail(String email, Pageable pageable);
}
