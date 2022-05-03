package com.shop.service;

import com.shop.model.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    List<OrderDetail> getCart();

    boolean hasCart();

    void addItem(OrderDetail orderDetail);

    void removeItem(Integer orderDetailId);

    void removeAll();

    void increaseQuantity(Integer orderDetailId);

    void decreaseQuantity(Integer orderDetailId);
}