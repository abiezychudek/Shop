package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private BigDecimal price;
    private String name;
    private String image;
      private int quantity_of_goods;
    public Item(BigDecimal price, String name, String image,int quantity_of_goods) {
        this.price = price;
        this.name = name;
        this.image = image;
   //     this.quantity_of_goods=quantity_of_goods;
    }
}
