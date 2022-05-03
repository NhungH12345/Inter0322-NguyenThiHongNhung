package com.shop.controller;

import com.shop.config.OrderStatusEnum;
import com.shop.model.Order;
import com.shop.model.OrderDetail;
import com.shop.model.User;
import com.shop.service.CartService;
import com.shop.service.OrderDetailService;
import com.shop.service.OrderService;
import com.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final OrderService orderService;
    private final HttpSession httpSession;
    private final CartService cartService;
    private final OrderDetailService orderDetailService;
    private final UserService userService;

    @GetMapping
    public String viewPaymentUI(Model model) {
        Object cart = this.httpSession.getAttribute("cart");
        if (Objects.isNull(cart) || ((List) cart).isEmpty()) {
            return "redirect:home";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            User user = this.userService.findByEmail(principal.getUsername());
            model.addAttribute("currentUser", user);
        }
        return "payment";
    }

    @PostMapping
    public String paymentO (Order order) {
        List<OrderDetail> cart = (List) this.httpSession.getAttribute("cart");

        order.setCreatedAt(Instant.now());
        order.setUpdatedAt(Instant.now());
        order.setStatus(OrderStatusEnum.ACTIVE.getValue());

        double total = 0.0;
        for (OrderDetail orderDetail : cart) {
            total += orderDetail.getQuantity() * orderDetail.getPrice();
        }
        order.setTotalPrice(total);
        Order saveOrder = this.orderService.saveOrder(order);
        cart.stream().map(orderDetail -> {
            orderDetail.setOrder(saveOrder);
            return orderDetail;
        }).forEach(this.orderDetailService::save);
        this.cartService.removeAll();
        return "redirect:home";
    }
}
