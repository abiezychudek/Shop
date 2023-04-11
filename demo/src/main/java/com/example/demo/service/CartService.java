package com.example.demo.service;

import com.example.demo.ShopClass.Cart;
import com.example.demo.ShopClass.CartItem;
import com.example.demo.model.Item;

import com.example.demo.repository.ItemRepository;

import java.math.BigDecimal;
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

    public void cleanAndDelete(){
        if(cart!=null) {
            if (cart.cartItems != null) {
                for (CartItem cartToChange : cart.cartItems) {
                    if (cartToChange.counter > 0) {
                        Item item = cartToChange.item;
                        if (item.getQuantity_of_goods() == cartToChange.counter) {
                            itemRepository.delete(item);
                        } else {
                            item.setQuantity_of_goods(item.getQuantity_of_goods() - cartToChange.counter);
                        }
                        cartToChange.counter = 0;
                        cartToChange.sum = BigDecimal.ZERO;

                    }
                }
            }
        }
        cart.getCartItems().removeAll(cart.cartItems);
        cart.counter=0;
        cart.sum=BigDecimal.ZERO;
    }
}
