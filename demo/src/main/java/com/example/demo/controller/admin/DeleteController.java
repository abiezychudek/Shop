package com.example.demo.controller.admin;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.swing.text.html.Option;

@Controller
@RequestMapping("/deleteItem")
public class DeleteController {

    private final ItemRepository itemRepository;

    @Autowired
    public DeleteController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String deleteItemPage(Model model){
        model.addAttribute("items",itemRepository.findAll() );
        return "/deleteItem";
    }

    @RequestMapping(value = "/{id}")
    public String deleteItem(@PathVariable("id") Long id){
        Optional<Item> itemOptional=itemRepository.findById(id);
        if(itemOptional.isPresent()){
            itemRepository.delete(itemOptional.get());
        }
        return "redirect:/";
    }
}
