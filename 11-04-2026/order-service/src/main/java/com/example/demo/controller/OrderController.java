package com.example.demo.controller;

import com.example.demo.dto.OrderResponse;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public OrderResponse create(@RequestBody Map<String, Object> req) {

        Long userId = Long.valueOf(req.get("userId").toString());
        Long productId = Long.valueOf(req.get("productId").toString());
        int quantity = Integer.parseInt(req.get("quantity").toString());

        return service.createOrder(userId, productId, quantity);
    }
}