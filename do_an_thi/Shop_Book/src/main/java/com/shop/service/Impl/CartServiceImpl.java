package com.shop.service.Impl;

import com.shop.model.OrderDetail;
import com.shop.model.Product;
import com.shop.repository.OrderDetailRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final HttpSession httpSession;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderDetail> getCart() {
        Object cart = httpSession.getAttribute("cart");
        if (Objects.isNull(cart)) {
            cart = new ArrayList<>();
        }
        return (List) cart;
    }

    @Override
    public boolean hasCart() {
        return httpSession.getAttribute("cart") != null;
    }

    @Override
    public void addItem(OrderDetail orderDetail) {
        List<OrderDetail> cart = this.getCart();
        Product product = this.productRepository.findById(orderDetail.getProduct().getId()).orElseThrow(() -> {
            return new RuntimeException("not found product: " + orderDetail.getProduct().getId());
        });
        Optional<OrderDetail> foundOrderDetailOpt = cart.stream().filter(od -> {
            return od.getProduct().getId() == orderDetail.getProduct().getId();
        }).findFirst();

        if (foundOrderDetailOpt.isPresent()) {
            OrderDetail foundOrderDetail = foundOrderDetailOpt.get();
            foundOrderDetail.setQuantity(orderDetail.getQuantity() + foundOrderDetail.getQuantity());
            return;
        }

        orderDetail.setProduct(product);
        orderDetail.setCreatedAt(Instant.now());
        orderDetail.setUpdatedAt(Instant.now());
        orderDetail.setPrice(product.getPrice());
        cart.add(orderDetail);
        httpSession.setAttribute("cart", cart);
    }

    @Override
    public void removeItem(Integer productId) {
        List<OrderDetail> cart = this.getCart();
        cart = cart.stream().filter(orderDetail -> orderDetail.getProduct().getId() != productId).collect(Collectors.toList());
        httpSession.setAttribute("cart", cart);
    }

    @Override
    public void removeAll() {
        httpSession.removeAttribute("cart");
    }

    @Override
    public void increaseQuantity(Integer orderDetailId) {
        OrderDetail orderDetail = this.findById(orderDetailId);
        orderDetail.setQuantity(orderDetail.getQuantity() + 1);
    }

    @Override
    public void decreaseQuantity(Integer orderDetailId) {
        OrderDetail orderDetail = this.findById(orderDetailId);
        orderDetail.setQuantity(orderDetail.getQuantity() - 1);
    }

    protected OrderDetail findById(Integer productId) {
        OrderDetail orderDetail = this.getCart().stream()
                .filter(od -> od.getProduct().getId() == productId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("not found product: " + productId));
        return orderDetail;
    }
}