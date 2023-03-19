package com.example.demo.ShopClass;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.*;

@Configuration
public class BeginItems implements CommandLineRunner {
    private final ItemRepository itemRepository;

    @Autowired
    public BeginItems(ItemRepository itemRepository){
        this.itemRepository=itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        itemRepository.saveAll(
                List.of(new Item(12345678910L,new BigDecimal("50.00"), "kot","https://tueuropa.pl/uploads/articles_files/2021/11/05/6e7f9516-1948-d9e8-ca22-00007380aca5.jpg",2)));
    }
}
