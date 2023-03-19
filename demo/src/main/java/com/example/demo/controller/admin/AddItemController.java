package com.example.demo.controller.admin;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/addItem")
public class AddItemController {
    private final ItemRepository itemRepository;

    @Autowired
    public AddItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    private String addItemPage() {
        return "/addItem";
    }
    @PostMapping
    private String addItem(Item item) {
        itemRepository.save(item);
        return "redirect:/";
    }

}
