package com.example.demo.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class OrderDto {
    private String FullName;
    private String city;
    private String post_code;
}
