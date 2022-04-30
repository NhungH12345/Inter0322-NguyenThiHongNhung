package com.shop.controller;

import com.shop.model.Order;
import com.shop.model.Product;
import com.shop.service.OrderDetailService;
import com.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    private OrderDetailService orderDetailService;
    @GetMapping()
    public ModelAndView getOrderList(@PageableDefault(size = 4) Pageable pageable, @RequestParam("search") Optional<String> search) {
        Page<Order> orders ;
        if (search.isPresent()) {
            orders = orderService.findOrderByCustomerNameContaining(search.get(), pageable);
        } else {
            orders = orderService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("Admin/customer/list");
        modelAndView.addObject("order", orders);
        return modelAndView;
    }
}
