package com.example.demo.service;

import com.example.demo.ShopClass.Cart;
import com.example.demo.model.Item;

import com.example.demo.repository.ItemRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final Cart cart;
    private final ItemRepository itemRepository;

    @Autowired
    public CartService(Cart cart, ItemRepository itemRepository) {
        this.cart = cart;
        this.itemRepository = itemRepository;

    }

    public void increaseItem(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            cart.add(item);
        }
    }

    public void decreaseItem(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            cart.remove(item);
        }
    }

    public void deleteItem(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            cart.delete(item);
        }
    }
}
