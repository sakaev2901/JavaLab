package ru.itis.demo.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer price;
    @ManyToOne
    @ToString.Exclude
    private ProductCategory productCategory;

}
