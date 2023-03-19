package com.example.demo.ShopClass;

import com.example.demo.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Getter
public class CartItem {
    public BigDecimal sum;
    public int counter;
    public Item item;

    public CartItem(Item item) {
        this.sum = item.getPrice();
        this.counter = 1;
        this.item = item;
    }

    public void increaseCounter() {
        counter++;
        sum();
    }

    public void decreaseCounter() {
        counter--;
        sum();
    }


    public void deleteCounter() {
        counter=0;
        sum=BigDecimal.ZERO;
    }
    public void sum() {
        sum = item.getPrice().multiply(new BigDecimal(counter));
    }
    public boolean samId(Item item){
        return item.equals(this.item);
    }

}
