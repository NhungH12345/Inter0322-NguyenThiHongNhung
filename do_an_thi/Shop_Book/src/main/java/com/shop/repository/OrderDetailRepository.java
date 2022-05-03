package com.shop.repository;

import com.shop.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM order_detail WHERE order_id_order = ?1")
    List<OrderDetail> findOrderDetailByOrderId(int id);

}
