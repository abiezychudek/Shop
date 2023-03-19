package com.example.demo.controller.user;

import com.example.demo.repository.OrderRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CartService cartService;

    @Autowired
    public OrderController(OrderRepository orderRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

   @GetMapping("/cart")
    public String order(){
        return "cartView";
    }

    @GetMapping("/increase/{id}")
    public String increaseOrder(@PathVariable("id") Long id){
            cartService.increaseItem(id);
            return "cartView";
    }

    @GetMapping("/decrease/{id}")
    public String decreaseOrder(@PathVariable("id") Long id){
        cartService.decreaseItem(id);
        return "cartView";
    }

    @GetMapping("/remove/{id}")
    public String removeOrder(@PathVariable("id") Long id){
        cartService.deleteItem(id);
        return "cartView";
    }

}
