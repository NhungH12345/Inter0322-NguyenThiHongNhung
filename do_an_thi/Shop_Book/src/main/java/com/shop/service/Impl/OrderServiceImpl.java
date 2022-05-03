package com.shop.service.Impl;

import com.shop.model.Order;
import com.shop.repository.OrderRepository;
import com.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);

    }
    @Override
    public Optional<Order> findOrderById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public Page<Order> findOrderByCustomerNameContaining(String customerName, Pageable pageable) {
        return orderRepository.findOrderByCustomerNameContaining(customerName, pageable);
    }

    @Override
    public Page<Order> findByEmail(String email, Pageable pageable) {
        return this.orderRepository.findByCustomerEmailLike("%" + email + "%", pageable);
    }
}
