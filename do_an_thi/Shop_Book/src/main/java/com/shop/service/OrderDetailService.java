package com.shop.service;

import com.shop.model.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    List<OrderDetail> findAll();
    Optional<OrderDetail> findByOrderId(int id);
    List<OrderDetail> findOrderDetailByOrderId(int id);
    OrderDetail save(OrderDetail orderDetail);
}
