package com.example.demo.controller.user;

import com.example.demo.ShopClass.Cart;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("")
public class HomeController {

    private final ItemRepository itemRepository;
    private final Cart cart;
    @Autowired
    public HomeController(ItemRepository itemRepository,Cart cart) {
        this.itemRepository = itemRepository;
        this.cart=cart;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items",itemRepository.findAll() );
        return "home";
    }
    @GetMapping("/add/{id}")
    public String addItemToCart(Model model, @PathVariable("id") Long id){
        Optional<Item> itemOptional=itemRepository.findById(id);
        if(itemOptional.isPresent()){
            cart.add(itemOptional.get());
        }
        model.addAttribute("items",itemRepository.findAll() );
        return "home";
    }
}
