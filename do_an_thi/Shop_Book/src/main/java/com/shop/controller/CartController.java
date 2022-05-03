package com.shop.controller;

import com.shop.model.OrderDetail;
import com.shop.service.CartService;
import com.shop.service.OrderDetailService;
import com.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final CartService cartService;

    public String getCart(Model model) {
        List<OrderDetail> cart = this.cartService.getCart();
        Double totalMoney = 0.0;
        for (OrderDetail orderDetail : cart) {
            totalMoney += orderDetail.getPrice() * orderDetail.getQuantity();
        }

        model.addAttribute("items", cart);
        model.addAttribute("totalMoney", totalMoney);
        return "cart";
    }

    @GetMapping
    public String viewCartUi(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        Order order = this.orderService.findByUsername(userDetails.getUsername());
//        List<OrderDetail> orderDetails = this.orderDetailService.findOrderDetailByOrderId(order.getId());
//
//        Double totalMoney = 0.0;
//        for (OrderDetail orderDetail : orderDetails) {
//            totalMoney += orderDetail.getPrice() * orderDetail.getQuantity();
//        }
//
//        model.addAttribute("items", orderDetails);
//        model.addAttribute("totalMoney", totalMoney);

        this.getCart(model);
        return "cart-layout";
    }

    @ResponseBody
    @PostMapping("/create")
    public String addItem(@RequestBody OrderDetail orderDetail) {
        this.cartService.addItem(orderDetail);
        return "";
    }

    @GetMapping("/delete/{productId}")
    public String removeItem(@PathVariable Integer productId, Model model) {
        this.cartService.removeItem(productId);
        return this.getCart(model);
    }

    @PostMapping("/increase")
    public String increaseItem(@RequestBody OrderDetail orderDetail, Model model) {
//        this.orderDetailService.increaseQuantity(idItem);
        this.cartService.increaseQuantity(orderDetail.getId());
        return this.getCart(model);
    }

    @PostMapping("/decrease")
    public String decreaseItem(@RequestBody OrderDetail orderDetail, Model model) {
//        this.orderDetailService.decreaseQuantity(idItem);
        this.cartService.decreaseQuantity(orderDetail.getId());
        return this.getCart(model);
    }

    @GetMapping("remove-all")
    public String removeAll(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        Order order = this.orderService.findByUsername(userDetails.getUsername());
//        this.orderDetailService.deleteByOrderId(order.getId());
        this.cartService.removeAll();
        return this.getCart(model);
    }
}