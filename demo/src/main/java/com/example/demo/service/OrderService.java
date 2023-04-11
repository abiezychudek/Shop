package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(OrderDto orderDto) {
        Order order=new Order(orderDto.getFullName(),orderDto.getCity(),orderDto.getPost_code());
        orderRepository.save(order);
    }
}
