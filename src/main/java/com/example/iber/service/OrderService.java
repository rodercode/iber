package com.example.iber.service;

import com.example.iber.model.Order;
import com.example.iber.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
@Transactional
    public Order createOrder(Order order){
        Order savedOrder = orderRepo.save(order);
        return savedOrder;
    }
}
