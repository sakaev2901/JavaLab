package com.example.demo.template;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
public class Car {
    private String _id;
    private String model;
    private String color;
    private Integer costInDollars;
}
