package com.shop.repository;

import com.shop.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("SELECT o FROM Order o ORDER BY o.totalPrice DESC")
    Page<Order> findAll(Pageable pageable);
    Page<Order> findOrderByCustomerNameContaining(String customerName,Pageable pageable);
    List<Order> findAll();
}
