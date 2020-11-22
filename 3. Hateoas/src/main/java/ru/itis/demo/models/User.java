package ru.itis.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @OneToOne
    private Cart cart;
//    @OneToMany
//    private List<Order> orders;


}
