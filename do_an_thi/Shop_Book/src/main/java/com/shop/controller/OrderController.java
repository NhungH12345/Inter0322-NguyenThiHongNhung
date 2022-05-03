package com.shop.controller;

import com.shop.model.Order;
import com.shop.model.Product;
import com.shop.service.OrderDetailService;
import com.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/order/histories")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public String getOrderHistories(Model model, @RequestParam(defaultValue = "1") Integer page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Page<Order> orders = this.orderService.findByEmail(userDetails.getUsername(), PageRequest.of(page - 1, 10));
            model.addAttribute("orders", orders);
        }
        return "order-histories";

    }
}
