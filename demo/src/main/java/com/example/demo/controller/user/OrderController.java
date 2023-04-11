package com.example.demo.controller.user;

import com.example.demo.ShopClass.Cart;
import com.example.demo.model.Order;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OrderService orderService;
    private final Cart cart;

    @Autowired
    public OrderController(OrderRepository orderRepository, CartService cartService, OrderService orderService, Cart cart) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.orderService = orderService;
        this.cart = cart;
    }

    @GetMapping("/cart")
    public String order() {
        return "cartView";
    }

    @GetMapping("/increase/{id}")
    public String increaseOrder(@PathVariable("id") Long id) {
        cartService.increaseItem(id);
        return "cartView";
    }

    @GetMapping("/decrease/{id}")
    public String decreaseOrder(@PathVariable("id") Long id) {
        cartService.decreaseItem(id);
        return "cartView";
    }

    @GetMapping("/remove/{id}")
    public String removeOrder(@PathVariable("id") Long id) {
        cartService.deleteItem(id);
        return "cartView";
    }

    @GetMapping("/summary")
    public String summary() {
        return "summary";
    }

    @PostMapping("/saveorder")
    public String saveOrder(OrderDto order) {
        //orderService.saveOrder(order);
        cart.clean();
        return "redirect:/";
    }

}

