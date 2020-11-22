package ru.itis.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany
    private List<Product> products;
//    @ManyToOne
//    private User user;
    private Date date;
    private Integer price;
}
