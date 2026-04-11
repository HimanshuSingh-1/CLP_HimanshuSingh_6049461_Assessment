package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    private Map<Long, Product> map = new HashMap<>();

    public ProductService() {
        Product p = new Product();
        p.setId(101L);
        p.setName("Keyboard");
        p.setPrice(1200);
        map.put(101L, p);
    }

    public Product getProduct(Long id) {
        return map.get(id);
    }
}