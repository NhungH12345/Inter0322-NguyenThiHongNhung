package com.shop.controller;

import com.shop.model.OrderDetail;
import com.shop.model.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.CartService;
import com.shop.service.OrderDetailService;
import com.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final CartService cartService;
    private final ProductRepository productRepository;

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
    public ResponseEntity addItem(@RequestBody OrderDetail orderDetail) {
        int quantity = orderDetail.getQuantity();
        int id = orderDetail.getProduct().getId();
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found product: " + id));

        Long productQuantity = product.getQuantity();

        if (quantity > productQuantity) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"không đủ hàng\"}");
        }

        product.setQuantity( (productQuantity - quantity) );
        this.productRepository.save(product);
        this.cartService.addItem(orderDetail);
        return ResponseEntity.ok("{\"msg\": \"Thêm vào giỏ hàng thành công\"}");
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