package com.shop.controller;

import com.shop.model.Order;
import com.shop.model.OrderDetail;
import com.shop.service.OrderDetailService;
import com.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    @GetMapping
    public String viewUI(Model model, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "") String keyword) {
        Pageable pageable = PageRequest.of(page - 1, 4);
        model.addAttribute("orders", this.orderService.findByEmail(keyword, pageable));
        return "Admin/order/list";
    }

    @GetMapping("/view/{orderId}")
    public String viewDetail(@PathVariable Integer orderId, Model model) {
        List<OrderDetail> orderDetails = this.orderDetailService.findOrderDetailByOrderId(orderId);
        Order order = this.orderService.findOrderById(orderId).orElseThrow(() -> new RuntimeException("not found order"));
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("order", order);
        //
        return "/Admin/order/detail-order";
    }
}
