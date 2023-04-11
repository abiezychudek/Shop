package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long id;
    private String FullName;
    private String city;
    private String post_code;

    public Order(String fullName, String city, String post_code) {
        this.FullName = fullName;
        this.city = city;
        this.post_code = post_code;
    }
}
