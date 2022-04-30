package com.shop.repository;

import com.shop.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query( value = "select order_detail.*\r\n" +
            "from `order`, order_detail where  `order`.id_order=order_detail.order_id_order and order_detail.order_id_order=: id_order",nativeQuery = true)
    List<OrderDetail> findOrderDetailByOrderId(int id);

}
