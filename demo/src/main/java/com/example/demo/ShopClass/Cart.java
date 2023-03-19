package com.example.demo.ShopClass;

import com.example.demo.ShopClass.CartItem;
import com.example.demo.model.Item;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
@Data
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    public List<CartItem> cartItems;
    public BigDecimal sum;
    public int counter;

    public Cart() {
        cartItems = new ArrayList<>();
        sum = BigDecimal.ZERO;
        counter = 0;
    }

    public void add(Item item) {
        boolean isAlready = false;
        for (CartItem cart : cartItems) {
            if (cart.samId(item)) {
                isAlready = true;
                cart.increaseCounter();
                break;
            }
        }

        if (!isAlready) {
            cartItems.add(new CartItem(item));}

        counter++;
        sum = sum.add(item.getPrice());
    }

    public void remove(Item item) {
        for (CartItem cart : cartItems) {
            if (cart.samId(item)) {
                cart.decreaseCounter();
                sum = sum.subtract(item.getPrice());
                counter--;
                    if (cart.getCounter() == 0) {
                        delete(item);
                     }
                break;
            }
        }
    }

    public void delete(Item item){
        for (CartItem cart : cartItems) {
            if (cart.samId(item)) {
                sum = sum.subtract(cart.getSum());
                counter-=cart.counter;
    //            cart.decreaseCounter();
            }
        }
        cartItems.removeIf(i -> i.samId(item));
    }

}
