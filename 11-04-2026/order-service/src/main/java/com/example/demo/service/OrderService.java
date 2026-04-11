package com.example.demo.service;

import com.example.demo.client.UserClient;
import com.example.demo.client.ProductClient;
import com.example.demo.dto.*;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private ProductClient productClient;
    
    
    @CircuitBreaker(name = "orderService", fallbackMethod = "fallbackOrder")
    public OrderResponse createOrder(Long userId, Long productId, int quantity) {

        User user = userClient.getUser(userId);
        Product product = productClient.getProduct(productId);

        OrderResponse res = new OrderResponse();
        res.setOrderId(5001L);
        res.setUserName(user.getName());
        res.setProductName(product.getName());
        res.setQuantity(quantity);
        res.setTotalPrice(product.getPrice() * quantity);

        return res;
    }
    
    
    public OrderResponse fallbackOrder(Long userId, Long productId, int quantity, Throwable ex) {

        OrderResponse response = new OrderResponse();

        response.setOrderId(0L);
        response.setUserName("Service Down");
        response.setProductName("Service Down");
        response.setQuantity(quantity);
        response.setTotalPrice(0);

        return response;
    }
    
}