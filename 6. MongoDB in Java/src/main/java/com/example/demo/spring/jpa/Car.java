package com.example.demo.spring.jpa;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "car")
public class Car {
    @Id
    private String _id;
    private String model;
    private String color;
    private Integer costInDollars;
}
