package com.example.demo.ShopClass;

import com.example.demo.ShopClass.CartItem;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
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
    private final ItemRepository itemRepository;

    public Cart(ItemRepository itemRepository) {
        cartItems = new ArrayList<>();
        sum = BigDecimal.ZERO;
        counter = 0;
        this.itemRepository = itemRepository;
    }

    public void add(Item item) {
        boolean isAlready = false;
        for (CartItem cart : cartItems) {
            if (cart.samId(item)) {
                isAlready = true;
                if(item.getQuantity_of_goods()>cart.counter){
                    cart.increaseCounter();
                    counter++;
                    sum = sum.add(item.getPrice());
                }else {
                    System.out.println("Wyczerpales nasz magazyn!");
                }
                break;
            }
        }

        if (!isAlready) {
            cartItems.add(new CartItem(item));
            counter++;
            sum = sum.add(item.getPrice());
        }


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
                cart.deleteCounter();
            }
        }
        cartItems.removeIf(i -> i.samId(item));
    }
    public void clean(){
        for(CartItem cart: cartItems){
            Item item=cart.item;
            int amount=item.getQuantity_of_goods()-cart.counter;
            item.setQuantity_of_goods(amount);
            if(item.getQuantity_of_goods()==0){
                itemRepository.delete(item);
            }
            itemRepository.save(item);
        }
        cartItems.clear();
        counter=0;
        sum=BigDecimal.ZERO;
    }

}
