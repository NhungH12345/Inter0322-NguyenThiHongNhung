package com.shop.service.Impl;

import com.shop.model.OrderDetail;
import com.shop.repository.OrderDetailRepository;
import com.shop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findByOrderId(int id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public OrderDetail findOrderDetailByOrderId(int id) {
        return findOrderDetailByOrderId(id);
    }
}
